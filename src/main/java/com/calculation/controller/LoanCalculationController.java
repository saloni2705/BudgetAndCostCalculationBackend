package com.calculation.controller;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculation.Repository.LoanCalculationRepository;
import com.calculation.Repository.UserRepository;
import com.calculation.Service.LoanCalculationService;
import com.calculation.Service.UserService;
import com.calculation.entity.LoanCalculation;
import com.calculation.entity.User;

@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true")
@RestController
@RequestMapping("/auth/loan")
public class LoanCalculationController {

    @Autowired
    private LoanCalculationService loanCalculationService;

    @Autowired
    private LoanCalculationRepository loanCalculationRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    @PostMapping("/calculate-emi/{userid}")
    public ResponseEntity<BigDecimal> calculateEMI(@PathVariable Long userid, @RequestBody LoanCalculation loanCalculation) {
        // Fetch the user from the database using the userId
    	   User user = userService.getUserById(userid).orElse(null);

        // Set the user for the loan calculation
        loanCalculation.setUser(user);

        // Perform any additional logic related to loan calculation here if needed

        BigDecimal emi = loanCalculationService.calculateEMI(loanCalculation);

        // Save the loan calculation result for the specific user
        loanCalculationRepository.save(loanCalculation);

        return new ResponseEntity<>(emi, HttpStatus.OK);
    }

}
