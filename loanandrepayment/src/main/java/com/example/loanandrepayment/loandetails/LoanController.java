package com.example.loanandrepayment.loandetails;

import com.example.loanandrepayment.loanrepayment.LoanRepayment;
import com.example.loanandrepayment.loanrepayment.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;
    @Autowired
    private LoanRepository loanRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createLoan(@RequestBody Loans loans) {
        try {
            Loans createdLoan = loanService.createLoan(loans);
            return new ResponseEntity<>(createdLoan, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Failed to create loan: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(loanRepository.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get loan: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllLoans() {
        try {
            return new ResponseEntity<>(loanRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get loans: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateLoan(@RequestBody Loans loans) {
        try {
            Loans updatedLoan = loanRepository.save(loans);
            return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update loan: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteLoan(@RequestParam Long id) {
        try{
            loanService.deleteLoan(id);
            return new ResponseEntity<>("loan deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete loan: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{loanId}/repayments")
    public ResponseEntity<?> makeRepayment(@PathVariable Long loanId, @RequestBody Double amount) {
        try {
            LoanRepayment repayment = RepaymentService.createLoanRepayment(loanId, amount);
            return new ResponseEntity<>(repayment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to make repayment: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{loanId}/repayments")
    public ResponseEntity<?> getRepayments(@PathVariable Long loanId) {
        try {
            List<LoanRepayment> repayments = RepaymentService.getLoanRepayments(loanId);
            return new ResponseEntity<>(repayments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get repayments: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}



