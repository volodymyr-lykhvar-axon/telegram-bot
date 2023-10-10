package com.telegram.integration.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * Feign Config Util.
 *
 * @author Volodymyr Lykhvar
 */
public final class FeignConfigUtil {

    private FeignConfigUtil() {
    }

    public static <T> T getObjectFromResponseBody(Response response, Class<T> targetClass, ObjectMapper objectMapper) {
        try {
            if (isNull(response.body())) {
                return null;
            }
            var result = new BufferedReader(new InputStreamReader(response.body().asInputStream()))
                    .lines()
                    .collect(Collectors.joining("\n"));
            return objectMapper.readValue(result, targetClass);
        } catch (IOException e) {
            return null;
        }
    }
}
