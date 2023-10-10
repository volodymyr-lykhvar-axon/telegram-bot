package com.telegram.api.converter;

import com.telegram.data.entity.currency.Currency;
import com.telegram.integration.model.CurrencyPriceResponse;

import java.util.Collection;
import java.util.List;

/**
 * Currency Converter.
 *
 * @author Volodymyr Lykhvar
 */
public final class CurrencyConverter {

    private CurrencyConverter() {
    }

    public static List<Currency> toCurrencies(Collection<CurrencyPriceResponse> currencyPriceResponse) {
        return currencyPriceResponse.stream()
                .map(CurrencyConverter::toCurrency)
                .toList();
    }

    public static Currency toCurrency(CurrencyPriceResponse currencyPriceResponse) {
        var currency = new Currency();
        currency.setId(currencyPriceResponse.getSymbol());
        currency.setPrice(currencyPriceResponse.getPrice());
        return currency;
    }
}
