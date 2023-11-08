package com.calculation.Service.impl;

import com.calculation.entity.Budget;
import com.calculation.entity.Expense;
import com.calculation.entity.User;

import io.jsonwebtoken.lang.Collections;

import com.calculation.Repository.BudgetRepository;
import com.calculation.Repository.ExpenseRepository;
import com.calculation.Service.BudgetService;
import com.calculation.Service.ExpenseService;
import com.calculation.Service.UserService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public Budget getBudgetById(Long budgetId) {
        return budgetRepository.findById(budgetId).orElse(null);
    }
    
    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;

    
    @Override
    public Map<String, BigDecimal> calculateBudgetCategorySpendingPercentages(Long budgetId) {
        Budget budget = getBudgetById(budgetId);

        if (budget == null || budget.getAllocatedAmount().compareTo(BigDecimal.ZERO) == 0) {
            return null;  // Return null instead of Collections.emptyMap()
        }

        Map<String, BigDecimal> categorySpendingPercentages = new HashMap<>();

        for (Expense expense : budget.getExpenses()) {
            String category = expense.getExpenseCategory();

            // Check if both category and budget category are not null
            if (category != null && budget.getBudgetCategory() != null && category.equals(budget.getBudgetCategory())) {
                BigDecimal totalSpendingInCategory = budget.getExpenses()
                        .stream()
                        .filter(e -> category.equals(e.getExpenseCategory()))
                        .map(Expense::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal categorySpendingPercentage = totalSpendingInCategory
                        .divide(budget.getAllocatedAmount(), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));

                categorySpendingPercentage = categorySpendingPercentage.setScale(2, RoundingMode.HALF_UP);

                categorySpendingPercentages.put(category, categorySpendingPercentage);
            }
        }

        return categorySpendingPercentages;
    }

    
    @Override
    public BigDecimal calculateNetSavings(Long userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        User user = userOptional.orElse(null);

        if (user == null) {
            // Handle the case where the user is not found
            return BigDecimal.ZERO;
        }

        List<Expense> allExpenses = expenseService.getAllExpensesByUser(user);

        BigDecimal totalExpenses = allExpenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalIncome = user.getTotalIncome();

        return totalIncome.subtract(totalExpenses);
    }

    
    @Override
    public List<Budget> getBudgetsByUserId(Long userId) {
        return budgetRepository.findByUserUserid(userId);
    }


    @Override
    public Budget updateBudgetAllocatedAmount(Long budgetId, BigDecimal newAmount) {
        Optional<Budget> optionalBudget = budgetRepository.findById(budgetId);

        if (optionalBudget.isPresent()) {
            Budget budget = optionalBudget.get();
            budget.setAllocatedAmount(newAmount);
            return budgetRepository.save(budget);
        } else {
            return null; 
        }
    }

	@Override
	public BigDecimal calculateBudgetCategorySpendingPercentage(Long budgetId) {
		// TODO Auto-generated method stub
		return null;
	}

}
