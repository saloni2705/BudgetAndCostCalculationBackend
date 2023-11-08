package com.calculation.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.calculation.entity.Budget;

public interface BudgetService {
    Budget saveBudget(Budget budget);
    Budget getBudgetById(Long budgetId);
    BigDecimal calculateBudgetCategorySpendingPercentage(Long budgetId);
	
    BigDecimal calculateNetSavings(Long userId);
    List<Budget> getBudgetsByUserId(Long userId);
    Budget updateBudgetAllocatedAmount(Long budgetId, BigDecimal newAmount);
	Map<String, BigDecimal> calculateBudgetCategorySpendingPercentages(Long budgetId);
}
