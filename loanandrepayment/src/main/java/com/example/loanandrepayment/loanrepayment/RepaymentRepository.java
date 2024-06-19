package com.example.loanandrepayment.loanrepayment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
     List<Repayment> findByLoanId(Long loanId);
}
