package com.example.loanandrepayment.loanrepayment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepaymentRepository extends JpaRepository<LoanRepayment, Long> {
     List<LoanRepayment> findByLoanId(Long loanId);
}
