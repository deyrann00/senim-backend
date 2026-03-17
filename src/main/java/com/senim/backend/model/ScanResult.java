package com.senim.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scan_results")
public class ScanResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inputType;

    @Column(columnDefinition = "TEXT")
    private String extractedText;

    private Double scamProbability;

    @Column(columnDefinition = "TEXT")
    private String detectedMethods;

    private LocalDateTime scannedAt = LocalDateTime.now();

    // Standard Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getInputType() { return inputType; }
    public void setInputType(String inputType) { this.inputType = inputType; }
    public String getExtractedText() { return extractedText; }
    public void setExtractedText(String extractedText) { this.extractedText = extractedText; }
    public Double getScamProbability() { return scamProbability; }
    public void setScamProbability(Double scamProbability) { this.scamProbability = scamProbability; }
    public String getDetectedMethods() { return detectedMethods; }
    public void setDetectedMethods(String detectedMethods) { this.detectedMethods = detectedMethods; }
    public LocalDateTime getScannedAt() { return scannedAt; }
    public void setScannedAt(LocalDateTime scannedAt) { this.scannedAt = scannedAt; }
}