package com.course.productservice.paymentservice.paymentgateway;

import com.razorpay.RazorpayException;

public interface PaymentGateway {
    public String generatePaymentLink(Long orderId, Long amount) throws RazorpayException;
}
