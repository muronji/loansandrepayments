package com.example.loanandrepayment.loanrepayment;

import com.example.loanandrepayment.loandetails.Loans;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Repayment {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @Column(nullable = false)
    private Double amountPaid;

    @Setter
    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Column(nullable = false)
    private Double remainingAmount = 0.0;

    @Setter
    @ManyToOne
    @JoinColumn(name= "loans_id", nullable=false)
    private Loans loan;


    public Repayment(){}


    public void setRemainingAmount(Double remainingAmount) {
    }
}
