package com.course.productservice.paymentservice.configs;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {
    //we have to read these keys form application.properties file using @value annotation
    @Value("${razorpay.key.id}")
    private String razorpayKeyId;
    @Value("${razorpay.key.secret}")
    private String razorpaySecretKey;
    @Bean
    public RazorpayClient getRazorpayInstance() throws RazorpayException {
        return new RazorpayClient(razorpayKeyId, razorpaySecretKey);

        //here key-id and secret-key-id are hard coded we will have to hide it from public for instance
        //if I host this project on github, then anyone can see these details and access my razorpay details that would be dangerous
        //therefore we will have to hide or protect this key from public, for this we will have to use environment variable that hold actual value
        //and we add those variable here instead of hard coded value.
    }
}
