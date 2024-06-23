package com.example.loanandrepayment.loandetails;

import com.example.loanandrepayment.dto.LoansDTO;
import com.example.loanandrepayment.transactions.TransactionRepository;
import com.example.loanandrepayment.transactions.Transactions;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final TransactionRepository transactionRepository;

    public LoanService(LoanRepository loanRepository, TransactionRepository transactionRepository) {
        this.loanRepository = loanRepository;
        this.transactionRepository = transactionRepository;
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

    @Transactional
        public Transactions repayment(Long loanId, Double amount) {
        Loans loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        double newBalance = loan.getBalance() - amount;
        if (newBalance < 0) {
            throw new RuntimeException("Loan fully paid off :)");
        }

        loan.setBalance(newBalance);
        loanRepository.save(loan);

        Transactions transaction = new Transactions();
        transaction.setLoan(loan);
        transaction.setAmount(amount);
        transaction.setType("repayment");
        transaction.setTransactionDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }


//    @Transactional
//    public Transactions deposit(Long loanId, Double amount) {
//        Loans loan = loanRepository.findById(loanId)
//                .orElseThrow(() -> new RuntimeException("Loan not found"));
//
//        Double newBalance = loan.getLoanAmount() - amount;
//        loan.setLoanAmount(newBalance);
//        loanRepository.save(loan);
//
//        Transactions transaction = new Transactions();
//        transaction.setLoan(loan);
//        transaction.setAmount(amount);
//        transaction.setType("deposit");
//        transaction.setTransactionDate(LocalDateTime.now());
//
//        return transactionRepository.save(transaction);
//    }

    public List<Transactions> getTransactionsByLoanId(Long loanId) {
        return transactionRepository.findByLoanId(loanId);
    }
    public List<Transactions> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
