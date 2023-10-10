package com.telegram.integration.model;

import java.math.BigDecimal;

/**
 * Currency Price Response.
 *
 * @author Volodymyr Lykhvar
 */
public class CurrencyPriceResponse {

    private String symbol;
    private BigDecimal price;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
