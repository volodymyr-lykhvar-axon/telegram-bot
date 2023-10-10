package com.file.core.service.currency;

import com.file.data.repository.currency.CurrencyRepository;
import com.file.integration.client.CurrencyClient;
import com.file.integration.exception.FeignErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.file.common.util.DateTimeUtil.nowLocalDateTime;
import static com.file.core.converter.CurrencyConverter.toCurrencies;

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
        var currencyPrices = toCurrencies(currencyClient.getCurrentPrice());
        currencyPrices.forEach(currency -> currency.setUpdatedAt(nowLocalDateTime()));
        currencyRepository.saveAll(currencyPrices);
    }
}
