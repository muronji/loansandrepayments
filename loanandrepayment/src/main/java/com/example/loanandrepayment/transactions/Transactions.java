package com.example.loanandrepayment.transactions;

import com.example.loanandrepayment.loandetails.Loans;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Transactions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private Loans loan;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String type; // repayment, withdrawal, deposit

    @Column(nullable = false)
    private LocalDateTime transactionDate;
}
