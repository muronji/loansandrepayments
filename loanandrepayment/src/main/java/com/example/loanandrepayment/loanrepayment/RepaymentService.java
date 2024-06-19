package com.example.loanandrepayment.loanrepayment;

import com.example.loanandrepayment.dto.RepaymentDTO;
import com.example.loanandrepayment.loandetails.LoanRepository;
import com.example.loanandrepayment.loandetails.Loans;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RepaymentService {

    private final RepaymentRepository repaymentRepository;
    private final LoanRepository loanRepository;

    public RepaymentService(RepaymentRepository repaymentRepository, LoanRepository loanRepository) {
        this.repaymentRepository = repaymentRepository;
        this.loanRepository = loanRepository;
    }

    @Transactional
    public Repayment createLoanRepayment(Long loanId, RepaymentDTO repaymentDTO) {
        Loans loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        Repayment loanRepayment = new Repayment();
        loanRepayment.setLoan(loan);
        loanRepayment.setAmountPaid(repaymentDTO.amountPaid());
        loanRepayment.setPaymentDate(LocalDateTime.now());

        Double remainingAmount = loan.getLoanAmount() - repaymentDTO.amountPaid();

        if (remainingAmount < 0) {
            throw new RuntimeException("Repayment amount exceeds the remaining loan amount.");
        }

        loanRepayment.setRemainingAmount(remainingAmount);

        Repayment savedLoanRepayment = repaymentRepository.save(loanRepayment);
        
        loan.setLoanAmount(remainingAmount);
        loanRepository.save(loan);

        return savedLoanRepayment;
    }

    public List<Repayment> getLoanRepayments(Long loanId) {
        return repaymentRepository.findByLoanId(loanId);
    }
}
