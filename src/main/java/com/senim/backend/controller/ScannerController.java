package com.senim.backend.controller;

import com.senim.backend.service.GroqService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/scan")
@CrossOrigin(origins = "*")
public class ScannerController {

    private final GroqService groqService;

    public ScannerController(GroqService groqService) {
        this.groqService = groqService;
    }

    @PostMapping("/text")
    public ResponseEntity<?> scanText(@RequestBody Map<String, String> payload) {
        try {
            String text = payload.get("text");
            return ResponseEntity.ok(groqService.analyzeText(text));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/image")
    public ResponseEntity<?> scanImage(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(groqService.analyzeImage(file));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}