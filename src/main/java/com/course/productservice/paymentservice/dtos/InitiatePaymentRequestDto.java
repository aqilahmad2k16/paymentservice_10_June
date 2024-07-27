package com.course.productservice.paymentservice.dtos;

import lombok.Data;

@Data
public class InitiatePaymentRequestDto {
    private Long orderId;
    private Long amount;
}
