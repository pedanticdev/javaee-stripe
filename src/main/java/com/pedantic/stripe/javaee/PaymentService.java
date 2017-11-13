/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pedantic.stripe.javaee;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import com.stripe.model.Charge;
/**
 *
 * @author pedantic
 */
@Stateless
public class PaymentService {


    @PostConstruct
    private void init() {
        Stripe.apiKey = "REPLACE_WITH_YOUR_OWN_KEY";
    }
    public Charge charge(String token, BigDecimal chargeAmount, String paymentCurrency) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        
        chargeParams.put("amount", chargeAmount);
        chargeParams.put("currency", paymentCurrency);
        chargeParams.put("source", token);

        return Charge.create(chargeParams);
    }
}
