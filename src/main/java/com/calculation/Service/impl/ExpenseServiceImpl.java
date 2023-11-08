package com.calculation.Service.impl;

import com.calculation.entity.Budget;
import com.calculation.entity.Expense;
import com.calculation.entity.User;
import com.calculation.Repository.ExpenseRepository;
import com.calculation.Service.ExpenseService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense getExpenseById(Long expenseId) {
        return expenseRepository.findById(expenseId).orElse(null);
    }
    
    @Override
    public BigDecimal calculateExpenseCategorySpendingPercentage(String category, User user) {
        List<Expense> expensesInCategory = expenseRepository.findByExpenseCategoryAndUser(category, user);

        if (expensesInCategory.isEmpty()) {
        
            return BigDecimal.ZERO;
        }

        BigDecimal totalExpensesInCategory = expensesInCategory.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Expense> allExpensesForUser = expenseRepository.findAllByUser(user);
        BigDecimal totalExpenses = allExpensesForUser.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalExpenses.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal percentage = totalExpensesInCategory
                .divide(totalExpenses, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));

        return percentage;
    }

    @Override
    public List<Expense> getAllExpensesByUser(User user) {
        return expenseRepository.findAllByUser(user);
    }
    
 
}
