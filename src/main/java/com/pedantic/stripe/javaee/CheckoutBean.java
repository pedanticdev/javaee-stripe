/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pedantic.stripe.javaee;

import java.io.IOException;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.omnifaces.cdi.Param;
import org.omnifaces.util.Faces;

/**
 *
 * @author pedantic
 */
@Named
@RequestScoped
public class CheckoutBean {
    //Price/total
    //Currency
    //Public Stripe API key

    @Inject
    private Model model;
    @Inject
    private Price price;
    @Inject
    private PaymentService paymentService;

    
    @Inject
    @Param
    private String stripeToken;
    @Inject
    @Param
    private String email;
    
    @PostConstruct
    private void init(){
        if (stripeToken != null && !stripeToken.isEmpty()) {

            createCharge();
        }
    }

    private void createCharge() {
        try {
            Charge charge = paymentService.charge(stripeToken, getTotal(), price.getCurrency());
            if (charge != null) {
                Faces.redirect("success.xhtml");
            }else {
                Faces.redirect("failure.xhtml");

            }
        } catch (StripeException | IOException e) {
            try {
                Faces.redirect("failure.xhtml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
    
    public BigDecimal getTotal() {
        model.setNumberOfHours(50);
        model.setMonth("Nov");
        BigDecimal total = price.getPricePerHour().multiply(new BigDecimal(model.getNumberOfHours()));
        return total.multiply(new BigDecimal("100"));
    }

    public String getCurrency() {
        return price.getCurrency();
    }
    
    public String getKey(){
        return "REPLACE_WITH_YOUR_OWN_KEY";
    }
    
    
    public String getMonth(){
        return model.getMonth();
    }

}
