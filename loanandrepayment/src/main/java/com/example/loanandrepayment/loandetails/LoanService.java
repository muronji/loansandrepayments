package com.example.loanandrepayment.loandetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public Loans createLoan(Loans loan) {
        return loanRepository.save(loan);
    }

    public Optional<Loans> getLoanById(Long id) {
        return loanRepository.findById(id);
    }
    public List<Loans> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loans updateLoan(Long id, Loans loan) {
        if (loanRepository.existsById(id)) {
            loan.setId(id);
            return loanRepository.save(loan);
        } else{
            throw new RuntimeException("loan id not found");
        }
    }

    public void deleteLoan(Long id) {
        if (loanRepository.existsById(id)) {
            loanRepository.deleteById(id);
        } else{
            throw new RuntimeException("loan id not found");
        }
    }
}
