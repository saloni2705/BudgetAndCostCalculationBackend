package com.calculation.Repository;

import com.calculation.entity.Portfolio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
	   Optional<Portfolio> findById(Long portfolioId);
   
}
