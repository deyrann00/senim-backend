package com.senim.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    private final String GROQ_API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Analyzes text directly
    public Map<String, Object> analyzeText(String text) throws Exception {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama-3.3-70b-versatile");
        requestBody.put("temperature", 0.1);
        requestBody.put("max_tokens", 512);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", "Ты эксперт по кибербезопасности Казахстана. Анализируешь тексты на мошенничество: финансовые пирамиды, фишинг, вишинг, поддельные госвыплаты (42500 тенге, ЕНПФ, Минтруд), фейковые розыгрыши. Отвечай ТОЛЬКО валидным JSON без markdown и пояснений."));
        messages.add(Map.of("role", "user", "content", "Проанализируй текст на мошенничество:\n\n\"\"\"\n" + text + "\n\"\"\"\n\nОтвет строго в JSON:\n{\n  \"score\": <число 0-100, процент риска>,\n  \"hits\": [\"конкретный триггер 1\", \"конкретный триггер 2\"],\n  \"safe\": <true если score < 30, иначе false>\n}"));
        requestBody.put("messages", messages);

        String responseContent = callGroqApi(requestBody);

        // Clean JSON formatting and parse it into a Map
        String cleanJson = responseContent.replaceAll("```json|```", "").trim();
        return objectMapper.readValue(cleanJson, Map.class);
    }

    // Handles the two-step Image process (OCR + Analysis)
    public Map<String, Object> analyzeImage(MultipartFile file) throws Exception {
        // 1. OCR (Image to Text)
        String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
        String dataUrl = "data:" + file.getContentType() + ";base64," + base64Image;

        Map<String, Object> ocrRequest = new HashMap<>();
        ocrRequest.put("model", "meta-llama/llama-4-scout-17b-16e-instruct");
        ocrRequest.put("temperature", 0.1);
        ocrRequest.put("max_tokens", 2048);

        Map<String, Object> imageContent = new HashMap<>();
        imageContent.put("type", "image_url");
        imageContent.put("image_url", Map.of("url", dataUrl));

        Map<String, Object> textContent = new HashMap<>();
        textContent.put("type", "text");
        textContent.put("text", "Ты OCR-система. Прочитай ВЕСЬ текст на этом скриншоте максимально точно. Сохрани оригинальный язык (казахский, русский или английский). Верни ТОЛЬКО извлечённый текст, без комментариев и пояснений.");

        ocrRequest.put("messages", List.of(Map.of("role", "user", "content", Arrays.asList(imageContent, textContent))));

        String extractedText = callGroqApi(ocrRequest);

        // 2. Text Analysis
        Map<String, Object> analysisResult = analyzeText(extractedText);

        // Attach the OCR text so the frontend can display it!
        analysisResult.put("extractedText", extractedText);
        return analysisResult;
    }

    // Helper method to execute the HTTP call to Groq
    private String callGroqApi(Map<String, Object> requestBody) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(GROQ_API_URL, entity, String.class);

        JsonNode rootNode = objectMapper.readTree(response.getBody());
        return rootNode.path("choices").get(0).path("message").path("content").asText().trim();
    }
}