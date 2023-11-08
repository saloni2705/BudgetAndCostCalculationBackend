package com.calculation.Service.impl;

import com.calculation.entity.Investment;
import com.calculation.Repository.InvestmentRepository;
import com.calculation.Service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Override
    public Investment saveInvestment(Investment investment) {
        return investmentRepository.save(investment);
    }

    @Override
    public Optional<Investment> getInvestmentById(Long investmentId) {
        return investmentRepository.findById(investmentId);
    }

    @Override
    public BigDecimal calculateFutureValue(Long investmentId) {
        Optional<Investment> optionalInvestment = getInvestmentById(investmentId);

        if (optionalInvestment.isPresent()) {
            Investment investment = optionalInvestment.get();
            BigDecimal r = investment.getAnnualInterestRate().divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP); // divide by 100
            BigDecimal purchasePrice = investment.getPurchasePrice();
            int n = investment.getCompoundingFrequency();
            int t = investment.getInvestmentPeriod();

            BigDecimal one = BigDecimal.ONE;
            BigDecimal effectiveRate = r.divide(BigDecimal.valueOf(n), 10, RoundingMode.HALF_UP);
         // Calculate the base (1+r)
            BigDecimal base = one.add(effectiveRate);
         // in start future value = initial purchase price
            BigDecimal futureValue = purchasePrice;

            // loop n * t times 
            for (int i = 0; i < n * t; i++) {
                futureValue = futureValue.multiply(base);
            }

            return futureValue.setScale(2, RoundingMode.HALF_UP);
        } else {
            // Handle the case where the investment is not found
            throw new NoSuchElementException("Investment not found with ID: " + investmentId);
        }
    }



}
