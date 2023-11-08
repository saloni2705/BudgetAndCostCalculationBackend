package com.calculation.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calculation.entity.LoanCalculation;
import com.calculation.entity.User;

public interface LoanCalculationRepository extends JpaRepository<LoanCalculation, Long> {
    List<LoanCalculation> findByUser(User user);
}

