package com.calculation.Repository;

import com.calculation.entity.Expense;
import com.calculation.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calculation.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.math.BigDecimal;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	 List<Expense> findByBudget(Budget budget);
	    
	    List<Expense> findByExpenseCategoryAndBudget(String category, Budget budget);

	    List<Expense> findByExpenseCategory(String category);
	    
	    List<Expense> findAllByUser(User user);
	    
	    List<Expense> findByExpenseCategoryAndUser(String category, User user);

	 
}