package com.calculation.entity;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "loan_calculation")
public class LoanCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loanid")
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    private String loanType;

    private BigDecimal principalLoanAmount;
    private BigDecimal annualInterestRate;
    private int numberOfInstallments;
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public BigDecimal getPrincipalLoanAmount() {
		return principalLoanAmount;
	}
	public void setPrincipalLoanAmount(BigDecimal principalLoanAmount) {
		this.principalLoanAmount = principalLoanAmount;
	}
	public BigDecimal getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(BigDecimal annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	public int getNumberOfInstallments() {
		return numberOfInstallments;
	}
	public void setNumberOfInstallments(int numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
	@Override
	public String toString() {
		return "LoanCalculation [loanId=" + loanId + ", user=" + user + ", loanType=" + loanType
				+ ", principalLoanAmount=" + principalLoanAmount + ", annualInterestRate=" + annualInterestRate
				+ ", numberOfInstallments=" + numberOfInstallments + "]";
	}
	public LoanCalculation(Long loanId, User user, String loanType, BigDecimal principalLoanAmount,
			BigDecimal annualInterestRate, int numberOfInstallments) {
		super();
		this.loanId = loanId;
		this.user = user;
		this.loanType = loanType;
		this.principalLoanAmount = principalLoanAmount;
		this.annualInterestRate = annualInterestRate;
		this.numberOfInstallments = numberOfInstallments;
	}
	public LoanCalculation() {
		super();
		// TODO Auto-generated constructor stub
	}

   
}
