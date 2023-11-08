package com.calculation.Service;

import com.calculation.entity.Investment;

import java.math.BigDecimal;
import java.util.Optional;

public interface InvestmentService {
    Investment saveInvestment(Investment investment);

    Optional<Investment> getInvestmentById(Long investmentId);

	BigDecimal calculateFutureValue(Long investmentId);

    
}
