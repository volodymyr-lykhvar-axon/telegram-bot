package com.telegram.api.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.telegram.api.deserializer.LocalDateTimeDeserializer;
import com.telegram.api.deserializer.StringTrimDeserializer;
import com.telegram.api.serializer.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;

/**
 * Beans Config.
 *
 * @author Volodymyr Lykhvar
 */
@Configuration
@EntityScan(basePackages = "com.telegram.data.entity")
@EnableFeignClients(basePackages = "com.telegram.integration.client")
@EnableJpaAuditing
@EnableJpaRepositories("com.telegram.data.repository")
public class BeansConfig {

    @Bean
    public ObjectMapper objectMapper() {
        var customModule = new SimpleModule()
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer())
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer())
                .addDeserializer(String.class, new StringTrimDeserializer());
        return JsonMapper.builder()
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .build()
                .registerModules(new JavaTimeModule(), customModule);
    }
}
