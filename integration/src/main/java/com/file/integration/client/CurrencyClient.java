package com.file.integration.client;

import com.file.integration.exception.FeignErrorException;
import com.file.integration.model.CurrencyPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Cryptocurrency client.
 *
 * @author Volodymyr Lykhvar
 */
@FeignClient(name = "currency-feign-client", url = "https://api.mexc.com/api/v3")
public interface CurrencyClient {

    @GetMapping("/ticker/price")
    List<CurrencyPriceResponse> getCurrentPrice() throws FeignErrorException;
}
