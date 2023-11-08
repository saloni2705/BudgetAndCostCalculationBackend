package com.calculation.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculation.Service.BudgetService;
import com.calculation.Service.UserService;
import com.calculation.entity.Budget;
import com.calculation.entity.User;

@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true")
@RestController
@RequestMapping("/auth/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;
    
    @Autowired
    private UserService userService;

    @PostMapping("/{userid}/createbudget")
    public ResponseEntity<?> createBudget(@PathVariable Long userid, @RequestBody Budget budget) {
        Optional<User> optionalUser = userService.getUserById(userid); 

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            budget.setUser(user);
            Budget savedBudget = budgetService.saveBudget(budget);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBudget);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }


    @GetMapping("/{budgetId}")
    public Budget getBudgetById(@PathVariable Long budgetId) {
        return budgetService.getBudgetById(budgetId);
    }

    
    @GetMapping("/netSavings/{userId}")
    public ResponseEntity<?> getNetSavings(@PathVariable Long userId) {
        BigDecimal netSavings = budgetService.calculateNetSavings(userId);

        return ResponseEntity.ok(netSavings);
    }

    @GetMapping("Users/{userId}")
    public List<Budget> getBudgetsByUser(@PathVariable Long userId) {
        return budgetService.getBudgetsByUserId(userId);
    }

    @PutMapping("/{budgetId}/updateAllocatedAmount")
    public ResponseEntity<?> updateAllocatedAmount(
        @PathVariable Long budgetId, @RequestBody Map<String, BigDecimal> requestBody) {
        BigDecimal newAmount = requestBody.get("newAmount");

        Budget updatedBudget = budgetService.updateBudgetAllocatedAmount(budgetId, newAmount);

        if (updatedBudget != null) {
            return ResponseEntity.ok(updatedBudget);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Budget not found.");
        }
    }
    
    @GetMapping("/{budgetId}/categorySpendingPercentage")
    public ResponseEntity<?> getBudgetCategorySpendingPercentage(@PathVariable Long budgetId) {
        Map<String, BigDecimal> result = budgetService.calculateBudgetCategorySpendingPercentages(budgetId);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Budget not found or allocated amount is zero.");
        }

        Map<String, String> formattedPercentages = new HashMap<>();

        for (Map.Entry<String, BigDecimal> entry : result.entrySet()) {
            BigDecimal categorySpendingPercentage = entry.getValue();
            String formattedPercentage = String.format("%.0f%%", categorySpendingPercentage.doubleValue());
            formattedPercentages.put(entry.getKey(), formattedPercentage);
        }

        return ResponseEntity.ok(formattedPercentages);
    }


   
}
