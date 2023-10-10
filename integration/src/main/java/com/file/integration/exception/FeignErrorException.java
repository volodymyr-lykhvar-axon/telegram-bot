package com.file.integration.exception;

import com.file.common.model.ServiceException;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static java.lang.String.format;

/**
 * The exception is thrown when external server responded with 4** HTTP code.
 *
 * @author Volodymyr Lykhvar
 */
public class FeignErrorException extends ServiceException {

    private static final String ERROR_MSG = "Server responded with error due to the reason: %s";

    public FeignErrorException(int status, String reason) {
        super(HttpStatus.valueOf(status), "error_request_failed", format(ERROR_MSG, reason));
    }

    public FeignErrorException(int status, String reason, String errorCode, Map<String, String> data) {
        super(HttpStatus.valueOf(status), errorCode, format(ERROR_MSG, reason), data);
    }
}
