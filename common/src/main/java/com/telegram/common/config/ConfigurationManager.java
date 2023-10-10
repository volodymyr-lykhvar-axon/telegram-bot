package com.telegram.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration Manager.
 *
 * @author Volodymyr Lykhvar
 */
@Component
@Validated
@ConfigurationProperties(prefix = "telegram")
public class ConfigurationManager {

    @NotNull
    @Valid
    private FeignTimeoutMillis feignTimeoutMillis;

    @NotNull
    @Valid
    private Settings settings;

    /**
     * Feign configuration properties.
     *
     * @author Volodymyr Lykhvar
     */
    private static class FeignTimeoutMillis {

        @NotNull
        @PositiveOrZero
        private Long connect;

        @NotNull
        @PositiveOrZero
        private Long read;

        public Long getConnect() {
            return connect;
        }

        public void setConnect(Long connect) {
            this.connect = connect;
        }

        public Long getRead() {
            return read;
        }

        public void setRead(Long read) {
            this.read = read;
        }
    }

    /**
     * Applications configuration properties.
     *
     * @author Volodymyr Lykhvar
     */
    private static class Settings {

        @NotNull
        @PositiveOrZero
        private Long maxUsers;

        @NotNull
        @Positive
        private Long priceSchedulerDelay;

        @NotNull
        @Positive
        private Integer notificationPercent;

        @NotNull
        @NotEmpty
        private String token;

        public Long getMaxUsers() {
            return maxUsers;
        }

        public void setMaxUsers(Long maxUsers) {
            this.maxUsers = maxUsers;
        }

        public Integer getNotificationPercent() {
            return notificationPercent;
        }

        public void setNotificationPercent(Integer notificationPercent) {
            this.notificationPercent = notificationPercent;
        }

        public Long getPriceSchedulerDelay() {
            return priceSchedulerDelay;
        }

        public void setPriceSchedulerDelay(Long priceSchedulerDelay) {
            this.priceSchedulerDelay = priceSchedulerDelay;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    private final List<String> publicPaths = new ArrayList<>();

    public List<String> getPublicPaths() {
        return publicPaths;
    }

    public Long getFeignConnectTimeoutMillis() {
        return feignTimeoutMillis.getConnect();
    }

    public Long getFeignReadTimeoutMillis() {
        return feignTimeoutMillis.getRead();
    }

    public FeignTimeoutMillis getFeignTimeoutMillis() {
        return feignTimeoutMillis;
    }

    public void setFeignTimeoutMillis(FeignTimeoutMillis feignTimeoutMillis) {
        this.feignTimeoutMillis = feignTimeoutMillis;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
