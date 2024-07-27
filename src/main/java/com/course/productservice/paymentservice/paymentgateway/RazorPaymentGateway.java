package com.course.productservice.paymentservice.paymentgateway;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RazorPaymentGateway implements PaymentGateway{
    private RazorpayClient razorpayClient;

    public RazorPaymentGateway(RazorpayClient razorpayClient){
        this.razorpayClient = razorpayClient;
    }
    @Override
    public String generatePaymentLink(Long orderId, Long amount) throws RazorpayException {
        //call the Razor pay Apis to generate payment link
        /*
        * the RazorpayClient class is used to create an instance of the Razorpay API client, which allows you to interact with Razorpay's payment gateway.

        YOUR_KEY_ID and YOUR_KEY_SECRET
        YOUR_KEY_ID: This is the API Key ID provided by Razorpay. It acts as a unique identifier for your Razorpay account and is used to authenticate API requests.
        YOUR_KEY_SECRET: This is the API Key Secret associated with your API Key ID. It is used in conjunction with the Key ID to securely authenticate API requests.
        * */
        //here you can see we are making this class tightly coupled with Razorclient object
        //we would create this client object by creating getInstance() and bean annotaion in config class
//        RazorpayClient razorpay = new RazorpayClient("rzp_test_flUDnkFTHim666", "8WEcZwMScPjQ1DsVzfDvGceP");

        /**
         these are the configuration parameter, these paramters will be sent to razorpay and based on these
         parameters razorpay will generate link for you
         */


        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount); //10.00
        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",1722171473); //expiry time of link, this timestamp
        paymentLinkRequest.put("reference_id",orderId.toString());
        paymentLinkRequest.put("description","Payment for orderId: " + orderId.toString());
        JSONObject customer = new JSONObject();
        customer.put("name","+918588086585");
        customer.put("contact","Aqil Ahmad");
        customer.put("email","er.aqilahmad031@gmail.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://leetcode.com/"); //this callback url, which will hit as the payment is succes if fail, it won't open
        //callback url, is url where do you want to nevigate as payment gets success or failur
        // i am adding url: "https://leetcode.com/" this will redirect to this url after payment gets success
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.toString();
    }
}
