package com.senim.backend.controller;

import com.senim.backend.model.ScanResult;
import com.senim.backend.repository.ScanResultRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scans")
@CrossOrigin(origins = {"http://localhost:5173", "https://senim-wiki.vercel.app"})
public class ScanResultController {

    private final ScanResultRepository scanResultRepository;

    public ScanResultController(ScanResultRepository scanResultRepository) {
        this.scanResultRepository = scanResultRepository;
    }

    @GetMapping
    public List<ScanResult> getAllScans() {
        return scanResultRepository.findAll();
    }

    @PostMapping
    public ScanResult saveScanResult(@RequestBody ScanResult scanResult) {
        return scanResultRepository.save(scanResult);
    }
}