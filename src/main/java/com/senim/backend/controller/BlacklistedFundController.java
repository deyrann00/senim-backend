package com.senim.backend.controller;

import com.senim.backend.model.BlacklistedFund;
import com.senim.backend.repository.BlacklistedFundRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funds")
@CrossOrigin(origins = {"http://localhost:5173", "https://senim-wiki.vercel.app", "http://localhost:3000"})
public class BlacklistedFundController {

    private final BlacklistedFundRepository fundRepository;

    public BlacklistedFundController(BlacklistedFundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    @GetMapping
    public List<BlacklistedFund> getAllFunds() {
        return fundRepository.findAll();
    }
}