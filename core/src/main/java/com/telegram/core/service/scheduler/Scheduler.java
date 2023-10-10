package com.telegram.core.service.scheduler;

import com.telegram.core.service.currency.CurrencyService;
import com.telegram.integration.exception.FeignErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Scheduler.
 *
 * @author Volodymyr Lykhvar
 */
@Component
public class Scheduler {

    private static final Logger LOG = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private CurrencyService currencyService;

    @PostConstruct
    private void init() throws FeignErrorException {
        currencyService.updateCurrentPrice();
    }

    @Scheduled(fixedRateString = "${telegram.settings.price-scheduler-delay}")
    public void updateCurrencyPrice() {
        try {
            currencyService.updateCurrentPrice();
        } catch (FeignErrorException e) {
            LOG.error("Currency prices were not updated. Reason: ", e);
        }
    }
}
