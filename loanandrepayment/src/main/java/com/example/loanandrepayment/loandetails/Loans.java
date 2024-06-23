package com.example.loanandrepayment.loandetails;

import com.example.loanandrepayment.transactions.Transactions;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Double loanAmount;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private String repaymentMethod;

    @Column (nullable = false)
    private Double balance;

    @PrePersist
    public void setDefaultBalance() {
        this.balance = this.loanAmount; // Set balance equal to loanAmount by default
    }


    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transactions> repayments = new ArrayList<>();



}
