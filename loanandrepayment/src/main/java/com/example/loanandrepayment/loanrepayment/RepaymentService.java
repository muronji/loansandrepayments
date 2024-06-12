package com.example.loanandrepayment.loanrepayment;

import com.example.loanandrepayment.loandetails.LoanRepository;
import com.example.loanandrepayment.loandetails.LoanService;
import com.example.loanandrepayment.loandetails.Loans;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RepaymentService {
    @Autowired
    private LoanService loanService;

    @Autowired
    private RepaymentRepository repaymentRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Transactional
    public LoanRepayment createLoanRepayment(Long loanId, Double amount) {
        Loans loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
        LoanRepayment loanRepayment = new LoanRepayment();
        loanRepayment.setAmountPaid(amount);
        loanRepayment.setPaymentDate(LocalDateTime.now());

        LoanRepayment savedLoanRepayment = repaymentRepository.save(loanRepayment);

        loan.setRemainingAmount(loan.getRemainingAmount() - amount);
        loanRepository.save(loan);

        return savedLoanRepayment;
    }

    public List<LoanRepayment> getLoanRepayments(Long loanId) {
        return repaymentRepository.findByLoanId(loanId);
    }
}
