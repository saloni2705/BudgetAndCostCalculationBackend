package com.calculation.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "expenseid")
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "budgetid", nullable = false)
    @JsonIgnore
    private Budget budget;

    private String expenseCategory;

    private BigDecimal amount;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String description;

	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", user=" + user + ", budget=" + budget + ", expenseCategory="
				+ expenseCategory + ", amount=" + amount + ", date=" + date + ", description=" + description + "]";
	}

	public Expense(Long expenseId, User user, Budget budget, String expenseCategory, BigDecimal amount, Date date,
			String description) {
		super();
		this.expenseId = expenseId;
		this.user = user;
		this.budget = budget;
		this.expenseCategory = expenseCategory;
		this.amount = amount;
		this.date = date;
		this.description = description;
	}

	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public String getExpenseCategory() {
		return expenseCategory;
	}

	public void setExpenseCategory(String expenseCategory) {
		this.expenseCategory = expenseCategory;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
