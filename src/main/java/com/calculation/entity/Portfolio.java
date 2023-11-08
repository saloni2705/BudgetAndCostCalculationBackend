package com.calculation.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "portfolio")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "portfolioid")
    private Long portfolioId;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnore
    private User user;

    @NotNull(message = "Portfolio Name is mandatory")
    private String portfolioName;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Investment> investments;

    // Constructors, getters, and setters

    public Long getPortfolioId() {
        return portfolioId;
    }

    @Override
	public String toString() {
		return "Portfolio [portfolioId=" + portfolioId + ", user=" + user + ", portfolioName=" + portfolioName
				+ ", description=" + description + ", creationDate=" + creationDate + "]";
	}

	public Portfolio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Portfolio(Long portfolioId, User user,
			@NotNull(message = "Portfolio Name is mandatory") String portfolioName, String description,
			Date creationDate, List<Investment> investments) {
		super();
		this.portfolioId = portfolioId;
		this.user = user;
		this.portfolioName = portfolioName;
		this.description = description;
		this.creationDate = creationDate;
		this.investments = investments;
	}

	public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }
}
