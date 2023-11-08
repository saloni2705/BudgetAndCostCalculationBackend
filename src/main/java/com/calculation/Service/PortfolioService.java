package com.calculation.Service;

import java.util.Optional;

import com.calculation.Repository.PortfolioRepository;
import com.calculation.entity.Portfolio;

public interface PortfolioService {
    Portfolio savePortfolio(Portfolio portfolio);
   
    Optional<Portfolio> getPortfolioById(Long portfolioId);

   
}
