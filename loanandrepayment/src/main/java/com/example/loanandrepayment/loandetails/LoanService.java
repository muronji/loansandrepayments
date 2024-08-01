package com.example.loanandrepayment.loandetails.;

import com.example.loanandrepayment.DTOs.LoansDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoansRepository loansRepository;

    public LoanService(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    public com.example.loanandrepayment.loans.Loans createLoan(LoansDTO loansDTO) {
        com.example.loanandrepayment.loans.Loans loan = new com.example.loanandrepayment.loans.Loans();
        loan.setCustomerId(loansDTO.customerId());
        loan.setLoanAmount(loansDTO.loanAmount());
        loan.setDuration(loansDTO.duration());
        loan.setRepaymentMethod(loansDTO.repaymentMethod());
        loan.setBalance(loansDTO.loanAmount());

        return loansRepository.save(loan);
    }

    public Optional<com.example.loanandrepayment.loans.Loans> getLoanById(Long id) {
        return loansRepository.findById(id);
    }

    public List<com.example.loanandrepayment.loans.Loans> getAllLoans() {
        return loansRepository.findAll();
    }

    public com.example.loanandrepayment.loans.Loans updateLoan(Long id, LoansDTO loansDTO) {
        Optional<com.example.loanandrepayment.loans.Loans> loanOptional = loansRepository.findById(id);
        if (loanOptional.isPresent()) {
            com.example.loanandrepayment.loans.Loans existingLoan = loanOptional.get();
            existingLoan.setCustomerId(loansDTO.customerId());
            existingLoan.setLoanAmount(loansDTO.loanAmount());
            existingLoan.setRepaymentMethod(loansDTO.repaymentMethod());
            existingLoan.setDuration(loansDTO.duration());
            existingLoan.setBalance(loansDTO.loanAmount());

            return loansRepository.save(existingLoan);
        } else {
            throw new RuntimeException("Loan not found");
        }
    }

    public void deleteLoan(Long id) {
        if (loansRepository.existsById(id)) {
            loansRepository.deleteById(id);
        } else {
            throw new RuntimeException("Loan ID not found");
        }
    }
}
