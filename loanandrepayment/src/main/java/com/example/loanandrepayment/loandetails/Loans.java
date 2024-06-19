package com.example.loanandrepayment.loandetails;

import com.example.loanandrepayment.loanrepayment.Repayment;
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

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Repayment> repayments = new ArrayList<>();



}
