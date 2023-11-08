package com.calculation.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "budgetid")
    private Long budgetId;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    private String budgetCategory;

    private BigDecimal allocatedAmount;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<Expense> expenses;

	@Override
	public String toString() {
		return "Budget [budgetId=" + budgetId + ", user=" + user + ", budgetCategory=" + budgetCategory
				+ ", allocatedAmount=" + allocatedAmount + ", expenses=" + expenses + "]";
	}

	public Budget(Long budgetId, User user, String budgetCategory, BigDecimal allocatedAmount, List<Expense> expenses) {
		super();
		this.budgetId = budgetId;
		this.user = user;
		this.budgetCategory = budgetCategory;
		this.allocatedAmount = allocatedAmount;
		this.expenses = expenses;
	}

	public Budget() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBudgetCategory() {
		return budgetCategory;
	}

	public void setBudgetCategory(String budgetCategory) {
		this.budgetCategory = budgetCategory;
	}

	public BigDecimal getAllocatedAmount() {
		return allocatedAmount;
	}

	public void setAllocatedAmount(BigDecimal allocatedAmount) {
		this.allocatedAmount = allocatedAmount;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	@OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Expense> Expenses;


   
}
