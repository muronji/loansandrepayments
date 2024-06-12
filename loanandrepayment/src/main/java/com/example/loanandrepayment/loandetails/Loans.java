package com.example.loanandrepayment.loandetails;

import jakarta.persistence.*;

@Entity
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Double loanAmount = 0.0;

    @Column(nullable = false)
    private Integer loanPeriodMonths = 1;

    @Column(nullable = false)
    private String repaymentMethod;

    @Column(nullable = false)
    private Double interestRate = 0.0;

    @Column(nullable = false)
    private Double remainingAmount = 0.0;

    // Getters and Setters
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }
    public Double getLoanAmount() {
        return loanAmount;
    }
    public void setLoanPeriodMonths(Integer loanPeriodMonths) {
        this.loanPeriodMonths = loanPeriodMonths;
    }
    public Integer getLoanPeriodMonths() {
        return loanPeriodMonths;
    }
    public void setRepaymentMethod(String repaymentMethod) {
        this.repaymentMethod = repaymentMethod;
    }
    public String getRepaymentMethod() {
        return repaymentMethod;
    }
    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
    public Double getInterestRate() {
        return interestRate;
    }

    public void setRemainingAmount(Double remainingAmount) {this.remainingAmount = remainingAmount;}
    public Double getRemainingAmount() {return remainingAmount;}


}
