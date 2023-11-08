package com.calculation.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "investment")
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "investmentid")
    private Long investmentId;

    @ManyToOne
    @JoinColumn(name = "portfolioid", nullable = false)
    @JsonIgnore
    private Portfolio portfolio;


    private BigDecimal quantity;

    private BigDecimal purchasePrice;

    @Temporal(TemporalType.DATE)
    private Date purchaseDate;

    private String investmentCategory;

    private BigDecimal annualInterestRate;
    private int compoundingFrequency; 
    private int investmentPeriod; // t - number of years the money is invested
	@Override
	public String toString() {
		return "Investment [investmentId=" + investmentId + ", portfolio=" + portfolio +
				 ", quantity=" + quantity + ", purchasePrice=" + purchasePrice + ", purchaseDate=" + purchaseDate
				+ ", investmentCategory=" + investmentCategory + ", annualInterestRate=" + annualInterestRate
				+ ", compoundingFrequency=" + compoundingFrequency + ", investmentPeriod=" + investmentPeriod + "]";
	}
	public Long getInvestmentId() {
		return investmentId;
	}
	public void setInvestmentId(Long investmentId) {
		this.investmentId = investmentId;
	}
	public Portfolio getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getInvestmentCategory() {
		return investmentCategory;
	}
	public void setInvestmentCategory(String investmentCategory) {
		this.investmentCategory = investmentCategory;
	}
	public BigDecimal getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(BigDecimal annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	public int getCompoundingFrequency() {
		return compoundingFrequency;
	}
	public void setCompoundingFrequency(int compoundingFrequency) {
		this.compoundingFrequency = compoundingFrequency;
	}
	public int getInvestmentPeriod() {
		return investmentPeriod;
	}
	public void setInvestmentPeriod(int investmentPeriod) {
		this.investmentPeriod = investmentPeriod;
	}
	public Investment(Long investmentId, Portfolio portfolio, String symbol, BigDecimal quantity,
			BigDecimal purchasePrice, Date purchaseDate, String investmentCategory, BigDecimal annualInterestRate,
			int compoundingFrequency, int investmentPeriod) {
		super();
		this.investmentId = investmentId;
		this.portfolio = portfolio;
		this.quantity = quantity;
		this.purchasePrice = purchasePrice;
		this.purchaseDate = purchaseDate;
		this.investmentCategory = investmentCategory;
		this.annualInterestRate = annualInterestRate;
		this.compoundingFrequency = compoundingFrequency;
		this.investmentPeriod = investmentPeriod;
	}
	public Investment() {
		super();
		// TODO Auto-generated constructor stub
	}

}
