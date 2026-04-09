package com.senim.backend.controller;

import com.senim.backend.model.BlacklistedFund;
import com.senim.backend.repository.BlacklistedFundRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funds")
@CrossOrigin(origins = "*")
public class BlacklistedFundController {

    private final BlacklistedFundRepository fundRepository;

    public BlacklistedFundController(BlacklistedFundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    @GetMapping
    public List<BlacklistedFund> getAllFunds() {
        return fundRepository.findAll();
    }

    @PostMapping
    public BlacklistedFund createFund(@RequestBody BlacklistedFund fund) {
        return fundRepository.save(fund);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlacklistedFund> updateFund(@PathVariable Long id, @RequestBody BlacklistedFund fundDetails) {
        return fundRepository.findById(id).map(fund -> {
            fund.setName(fundDetails.getName());
            fund.setName_kz(fundDetails.getName_kz());
            fund.setName_ru(fundDetails.getName_ru());
            fund.setDescription(fundDetails.getDescription());
            fund.setDescription_kz(fundDetails.getDescription_kz());
            fund.setDescription_ru(fundDetails.getDescription_ru());
            fund.setReason(fundDetails.getReason());
            fund.setReason_kz(fundDetails.getReason_kz());
            fund.setReason_ru(fundDetails.getReason_ru());
            fund.setStatus(fundDetails.getStatus());
            return ResponseEntity.ok(fundRepository.save(fund));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFund(@PathVariable Long id) {
        return fundRepository.findById(id).map(fund -> {
            fundRepository.delete(fund);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}