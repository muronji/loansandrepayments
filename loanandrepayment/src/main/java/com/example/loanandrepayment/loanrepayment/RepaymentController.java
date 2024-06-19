package com.example.loanandrepayment.loanrepayment;

import com.example.loanandrepayment.dto.RepaymentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
public class RepaymentController {

   private final RepaymentService repaymentService;

   public RepaymentController(RepaymentService repaymentService){
       this.repaymentService = repaymentService;
   }


    @PostMapping("/{loanId}/repayments")
    public ResponseEntity<?> makeRepayment(@PathVariable Long loanId, @RequestBody RepaymentDTO repaymentDTO) {
        try {
            Repayment repayment = repaymentService.createLoanRepayment(loanId, repaymentDTO);
            return new ResponseEntity<>(repayment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to make repayment: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{loanId}/repayments")
    public ResponseEntity<?> getRepayments(@PathVariable Long loanId) {
        try {
            List<Repayment> repayments = repaymentService.getLoanRepayments(loanId);
            return new ResponseEntity<>(repayments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get repayments: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
