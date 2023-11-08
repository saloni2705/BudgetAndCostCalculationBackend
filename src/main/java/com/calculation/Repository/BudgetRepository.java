package com.calculation.Repository;

import com.calculation.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUserUserid(Long userId);
    
    Optional<Budget> findByBudgetId(Long budgetId);
}
