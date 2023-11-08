package com.calculation.controller;

import com.calculation.entity.Expense;
import com.calculation.entity.User;
import com.calculation.entity.Budget;
import com.calculation.Service.BudgetService;
import com.calculation.Service.ExpenseService;
import com.calculation.Service.UserService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true")
@RestController
@RequestMapping("/auth/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    
    @Autowired
    private BudgetService budgetService;

    @Autowired
    private UserService userService;


    
    @PostMapping(value = "{userid}/{budgetId}/addExpense")
    public ResponseEntity<?> addExpense(
        @PathVariable Long userid,
        @PathVariable Long budgetId,
        @RequestBody @Valid Expense expense,
        BindingResult bindingResult) {

        // Validate the expense object
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid expense data.");
        }

        // Check if the user and budget exist
        User user = userService.getUserById(userid).orElse(null);
        Budget budget = budgetService.getBudgetById(budgetId);

        if (user == null || budget == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or budget not found.");
        }

        expense.setUser(user);
        expense.setBudget(budget);

        if (expense.getDate() == null) {
            expense.setDate(new Date());
        }

        Expense savedExpense = expenseService.saveExpense(expense);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedExpense);
    }
    
    @GetMapping("/{expenseId}")
    public Expense getExpenseById(@PathVariable Long expenseId) {
        return expenseService.getExpenseById(expenseId);
    }

    
    @GetMapping("/categorySpendingPercentage/{userId}/{category}")
    public ResponseEntity<?> getExpenseCategorySpendingPercentage(
            @PathVariable Long userId,
            @PathVariable String category) {
        User user = userService.getUserById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
        }

        BigDecimal categorySpendingPercentage = expenseService.calculateExpenseCategorySpendingPercentage(category, user);

        if (categorySpendingPercentage.compareTo(BigDecimal.ZERO) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No expenses found for the category: " + category);
        }

        // Format the percentage as a string with no decimal places and a percentage symbol
        String formattedPercentage = categorySpendingPercentage.setScale(0, RoundingMode.HALF_UP) + "%";

        return ResponseEntity.ok(formattedPercentage);
    }


}
