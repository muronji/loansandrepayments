package com.example.loanandrepayment.loandetails;

import com.example.loanandrepayment.dto.LoansDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loans createLoan(LoansDTO loansDTO) {
        Loans loan = new Loans();
        loan.setCustomerId(loansDTO.customerId());
        loan.setLoanAmount(loansDTO.loanAmount());
        loan.setDuration(loansDTO.duration());
        loan.setRepaymentMethod(loansDTO.repaymentMethod());

        return loanRepository.save(loan);
    }

    public Optional<Loans> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    public List<Loans> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loans updateLoan(Long id, LoansDTO loansDTO) {
        Optional<Loans> loanOptional = loanRepository.findById(id);
        if (loanOptional.isPresent()) {
            Loans existingLoan = loanOptional.get();
            existingLoan.setLoanAmount(loansDTO.loanAmount());
            existingLoan.setRepaymentMethod(loansDTO.repaymentMethod());
            existingLoan.setDuration(loansDTO.duration());
            return loanRepository.save(existingLoan);
        } else {
            throw new RuntimeException("Loan not found");
        }
    }

    public void deleteLoan(Long id) {
        if (loanRepository.existsById(id)) {
            loanRepository.deleteById(id);
        } else {
            throw new RuntimeException("Loan ID not found");
        }
    }
}
