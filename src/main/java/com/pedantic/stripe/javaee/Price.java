/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pedantic.stripe.javaee;

import java.math.BigDecimal;

/**
 *
 * @author pedantic
 */
public class Price {
    private final BigDecimal pricePerHour = new BigDecimal("2");
    private String currency = "usd";

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }
    
    
    
}
