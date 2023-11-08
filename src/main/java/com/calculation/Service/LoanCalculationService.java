package com.calculation.Service;
import java.math.BigDecimal;
import java.util.List;

import com.calculation.entity.LoanCalculation;

public interface LoanCalculationService {
    BigDecimal calculateEMI(LoanCalculation loanCalculation);
    List<LoanCalculation> getLoansByUserId(Long userid);
}
