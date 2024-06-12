package com.example.loanandrepayment.loanrepayment;

import com.example.loanandrepayment.loandetails.Loans;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class LoanRepayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Double amountPaid;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name= "loans_id", nullable=false)
    private Loans loans;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loans getLoan() {

        return loans;
    }



    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}


