package com.example.loanandrepayment.transactions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
     List<Transactions> findByLoanId(Long loanId);
}
