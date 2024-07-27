package com.course.productservice.paymentservice.services;

import com.course.productservice.paymentservice.paymentgateway.PaymentGateway;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentGateway paymentGateway;

    //using construction injection
    public PaymentService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }
    public String initiatePayment(Long orderId, Long amount) throws RazorpayException {
        //make a call to payment gateway to generate payment link
        return paymentGateway.generatePaymentLink(
                orderId,
                amount
        );
    }

    /*
    Could not autowire. There is more than one bean of 'PaymentGateway' type.
    Beans:
    razorPaymentGateway   (RazorPaymentGateway.java) stripePaymentGateway   (StripePaymentGateway.java)
    The error "Could not autowire. There is more than one bean of 'PaymentGateway' type"
    indicates that Spring cannot decide which bean to inject because there are multiple beans of the same type
    i) use of @Primary annotation: Mark one of the beans as the primary bean to be injected when there is a conflict.
    @Component
    @Primary
    public class RazorPaymentGateway implements PaymentGateway {
    // Implementation details
    }

    ii) using @Qualifier annotation: Use the @Qualifier annotation to specify which bean should be injected.
     private final PaymentGateway paymentGateway;

    @Autowired
    public PaymentService(@Qualifier("razorPaymentGateway") PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
     */
}
