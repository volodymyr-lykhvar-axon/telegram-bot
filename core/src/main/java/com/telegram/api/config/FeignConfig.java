package com.telegram.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telegram.common.config.ConfigurationManager;
import com.telegram.common.model.ServiceResponseBody;
import com.telegram.integration.exception.FeignErrorException;
import feign.Contract;
import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

import static com.telegram.integration.util.FeignConfigUtil.getObjectFromResponseBody;
import static feign.FeignException.errorStatus;
import static java.util.Objects.nonNull;

/**
 * Common configuration for Feign clients.
 *
 * @author Volodymyr Lykhvar
 */
@Configuration
public class FeignConfig {

    private static final int RETRY_ATTEMPTS = 5;

    @Autowired
    private ConfigurationManager configurationManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public Contract feignContract() {
        return new SpringMvcContract();
    }

    @Bean
    public FeignErrorDecoder errorDecoder() {
        return new FeignErrorDecoder(objectMapper);
    }

    @Bean
    public FeignRetryer feignRetryer() {
        return new FeignRetryer(RETRY_ATTEMPTS);
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(
                configurationManager.getFeignConnectTimeoutMillis(),
                TimeUnit.MILLISECONDS,
                configurationManager.getFeignReadTimeoutMillis(),
                TimeUnit.MILLISECONDS,
                true);
    }

    @Bean
    public feign.Logger.Level feignLoggerLevel() {
        return feign.Logger.Level.NONE;
    }

    /**
     * Custom {@link ErrorDecoder} implementation to decode HTTP responses with status other than 200.
     */
    public static class FeignErrorDecoder implements ErrorDecoder {

        private static final Logger LOG = LoggerFactory.getLogger(FeignErrorDecoder.class);

        private final ObjectMapper objectMapper;

        public FeignErrorDecoder(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public Exception decode(String methodKey, Response response) {
            LOG.debug("Error processing feign response from {}", response.request().url());
            var errorResponse = getObjectFromResponseBody(response, ServiceResponseBody.class, objectMapper);
            if (nonNull(errorResponse)) {
                if (nonNull(errorResponse.getCode())) {
                    return new FeignErrorException(
                            response.status(),
                            errorResponse.getMessage(),
                            errorResponse.getCode(),
                            errorResponse.getData()
                    );
                }
                return new FeignErrorException(response.status(), errorResponse.getMessage());
            }
            var feignException = errorStatus(methodKey, response);
            return new FeignErrorException(feignException.status(), feignException.getMessage());
        }
    }

    /**
     * Custom {@link Retryer} implementation to enable token renewal logic.
     *
     * @author Volodymyr Lykhvar
     */
    public static class FeignRetryer implements Retryer {

        private static final Logger LOG = LoggerFactory.getLogger(FeignRetryer.class);

        private final int retryAttempts;
        private int attempt;

        FeignRetryer(int retryAttempts) {
            this.retryAttempts = retryAttempts;
            this.attempt = 1;
        }

        @Override
        public void continueOrPropagate(RetryableException e) {
            if (attempt++ >= this.retryAttempts) {
                LOG.error("Request has failed after {} retries", retryAttempts);
                throw e;
            }
        }

        @Override
        public Retryer clone() {
            return new FeignRetryer(retryAttempts);
        }
    }
}
