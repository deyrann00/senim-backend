package com.senim.backend.repository;

import com.senim.backend.model.BlacklistedFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistedFundRepository extends JpaRepository<BlacklistedFund, Long> {
}