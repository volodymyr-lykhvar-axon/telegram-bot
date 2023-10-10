package com.telegram.core.service.currency;

import com.telegram.data.repository.currency.CurrencyRepository;
import com.telegram.integration.client.CurrencyClient;
import com.telegram.integration.exception.FeignErrorException;
import com.telegram.core.converter.CurrencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.telegram.common.util.DateTimeUtil.nowLocalDateTime;

/**
 * Currency Service.
 *
 * @author Volodymyr Lykhvar
 */
@Service
public class CurrencyService {

    @Autowired
    private CurrencyClient currencyClient;

    @Autowired
    private CurrencyRepository currencyRepository;

    /**
     * Update all currency prince.
     *
     * @throws FeignErrorException when error happened during request processing
     */
    public void updateCurrentPrice() throws FeignErrorException {
        var currencyPrices = CurrencyConverter.toCurrencies(currencyClient.getCurrentPrice());
        currencyPrices.forEach(currency -> currency.setUpdatedAt(nowLocalDateTime()));
        currencyRepository.saveAll(currencyPrices);
    }
}
