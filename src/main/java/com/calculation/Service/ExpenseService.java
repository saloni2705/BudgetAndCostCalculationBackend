package com.calculation.Service;

import java.math.BigDecimal;
import java.util.List;

import com.calculation.entity.Budget;
import com.calculation.entity.Expense;
import com.calculation.entity.User;

public interface ExpenseService {
   
    Expense getExpenseById(Long expenseId);
    Expense saveExpense(Expense expense);  
    List<Expense> getAllExpensesByUser(User user);
	BigDecimal calculateExpenseCategorySpendingPercentage(String category, User user);
}
