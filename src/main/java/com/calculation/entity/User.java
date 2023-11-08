package com.calculation.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private Long userid;

   
    public User(Long userid) {
        super();
        this.userid = userid;
    }

    public User(Long userid, @NotNull(message = "First name is mandatory") String name) {
        super();
        this.userid = userid;
        this.name = name;
    }

    public User(Long userid, @NotNull(message = "First name is mandatory") String name,
            @NotNull(message = "Email is mandatory") @Email(message = "Require email format") String email) {
        super();
        this.userid = userid;
        this.name = name;
        this.email = email;
    }


    public User(@NotNull(message = "First name is mandatory") String name,
                @NotNull(message = "Email is mandatory") @Email(message = "Require email format") String email,
                @Size(max = 10, min = 10, message = "Require only 10 digits") String phoneNumber,
                @NotNull(message = "Password is mandatory") String password,
                @NotNull(message = "Income is mandatory") BigDecimal totalIncome) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.totalIncome = totalIncome;
    }


	public User(Long userid, @NotNull(message = "First name is mandatory") String name,
            @NotNull(message = "Email is mandatory") @Email(message = "Require email format") String email,
            @Size(max = 10, min = 10, message = "Require only 10 digits") String phoneNumber) {
        super();
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @NotNull(message = "First name is mandatory")
    private String name;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Require email format")
    private String email;

    @Size(max = 10, min = 10, message = "Require only 10 digits")
    private String phoneNumber;

    @NotNull(message = "Password is mandatory")
    private String password;
    
    public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", password=" + password + ", totalIncome=" + totalIncome + "]";
	}

	public User(Long userid, @NotNull(message = "First name is mandatory") String name,
			@NotNull(message = "Email is mandatory") @Email(message = "Require email format") String email,
			@Size(max = 10, min = 10, message = "Require only 10 digits") String phoneNumber,
			@NotNull(message = "Password is mandatory") String password,
			@NotNull(message = "Income is mandatory") BigDecimal totalIncome) {
		super();
		this.userid = userid;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.totalIncome = totalIncome;
	}

	@NotNull(message = "Income is mandatory")
    private BigDecimal totalIncome;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long customerid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public User() {
        // default constructor
    }
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Expense> expenses;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Budget> budgets;


	public User(List<Budget> budgets) {
		super();
		this.budgets = budgets;
	}

}
