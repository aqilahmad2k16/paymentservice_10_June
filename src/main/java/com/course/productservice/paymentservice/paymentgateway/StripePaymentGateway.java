package com.course.productservice.paymentservice.paymentgateway;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway{
    @Override
    public String generatePaymentLink(Long orderId, Long amount) {
        //from here I have to Stripe Apis to generate payment link;
        return null;
    }
}
