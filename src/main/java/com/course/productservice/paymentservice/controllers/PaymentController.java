package com.course.productservice.paymentservice.controllers;

import com.course.productservice.paymentservice.dtos.InitiatePaymentRequestDto;
import com.course.productservice.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    /*
    here we are returning string because, we will return final link that is in the string type
     */
    @PostMapping("/")
    public String initiatesPayment(@RequestBody InitiatePaymentRequestDto requestDto){
        try{
            return paymentService.initiatePayment(requestDto.getOrderId(),
                    requestDto.getAmount());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
        //"short_url": "https://rzp.io/i/Spk4QaaCH" this the payment link that razaorpay has generated and (return) send to frontend and when user click on
        //payment button it will rediredt user to this short-url link
    }
}
