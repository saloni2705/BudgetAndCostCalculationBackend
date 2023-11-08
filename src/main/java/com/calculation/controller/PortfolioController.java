package com.calculation.controller;

import com.calculation.entity.Investment;
import com.calculation.entity.Portfolio;

import com.calculation.entity.User;
import com.calculation.Service.InvestmentService;
import com.calculation.Service.PortfolioService;
import com.calculation.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.math.BigDecimal;
import java.util.Date;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true")
@RestController
@RequestMapping("/auth/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private InvestmentService investmentService;


    @PostMapping("/createPortfolio/{userid}")
    public ResponseEntity<?> createPortfolio(
            @PathVariable Long userid,
            @RequestBody @Valid Portfolio portfolio,
            BindingResult bindingResult) {

       
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid portfolio data.");
        }

        
        User user = userService.getUserById(userid).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userid);
        }

        portfolio.setUser(user);
        portfolio.setCreationDate(new Date());
    
        Portfolio createdPortfolio = portfolioService.savePortfolio(portfolio);

        String successMessage = "Your portfolio ID is " + createdPortfolio.getPortfolioId();

        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }

    
    @PostMapping("/createInvestment/{portfolioId}")
    public ResponseEntity<?> createInvestment(
            @PathVariable Long portfolioId,
            @RequestBody @Valid Investment investment,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid investment data.");
        }

        Portfolio portfolio = portfolioService.getPortfolioById(portfolioId).orElse(null);
        if (portfolio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Portfolio not found with ID: " + portfolioId);
        }

        investment.setPortfolio(portfolio);
        investment.setPurchaseDate(new Date());

        // Save the investment
        Investment createdInvestment = investmentService.saveInvestment(investment);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvestment);
    }

    @GetMapping("/calculateFutureValue/{investmentId}")
    public ResponseEntity<?> calculateFutureValue(@PathVariable Long investmentId) {
        try {
            BigDecimal futureValue = investmentService.calculateFutureValue(investmentId);
            return ResponseEntity.ok(futureValue);
        } catch ( NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
