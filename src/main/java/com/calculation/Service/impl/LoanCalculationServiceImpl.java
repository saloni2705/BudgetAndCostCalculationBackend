package com.calculation.Service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calculation.Repository.InvestmentRepository;
import com.calculation.Repository.LoanCalculationRepository;
import com.calculation.Repository.UserRepository;
import com.calculation.Service.LoanCalculationService;
import com.calculation.entity.LoanCalculation;
import com.calculation.entity.User;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoanCalculationServiceImpl implements LoanCalculationService {

	  @Autowired
	    private LoanCalculationRepository loanCalculation;
	  
	  @Autowired
	    private UserRepository userRepository;
	
    @Override
    public BigDecimal calculateEMI(LoanCalculation loanCalculation) {
        BigDecimal principalLoanAmount = loanCalculation.getPrincipalLoanAmount();
        BigDecimal annualInterestRate = loanCalculation.getAnnualInterestRate();
        int numberOfInstallments = loanCalculation.getNumberOfInstallments();

        // Convert annual interest rate to monthly and then to a decimal
        BigDecimal monthlyInterestRate = annualInterestRate.divide(BigDecimal.valueOf(12), 4, BigDecimal.ROUND_HALF_UP)
                                                           .divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_HALF_UP);

        // Calculate EMI 
        BigDecimal numerator = monthlyInterestRate.multiply(principalLoanAmount);
        BigDecimal denominator = BigDecimal.ONE.subtract(BigDecimal.ONE.divide(monthlyInterestRate.add(BigDecimal.ONE).pow(numberOfInstallments), 4, BigDecimal.ROUND_HALF_UP));

        return numerator.divide(denominator, 2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public List<LoanCalculation> getLoansByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return loanCalculation.findByUser(user);
    }
}
