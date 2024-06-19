package com.example.loanandrepayment.dto;

public record LoansDTO(
        Long customerId,
        Double loanAmount,
        Integer duration,
        String repaymentMethod
) {


}
