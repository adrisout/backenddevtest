package com.adrian.backenddevtest.handler;

import com.adrian.backenddevtest.exception.ProductNotFoundException;
import com.adrian.backenddevtest.exception.RestClientException;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    private static final String RESPONSE_400_MESSAGE = "Bad arguments in request";

    private static final String RESPONSE_500_MESSAGE = "Internal server error";

    private static final String RESPONSE_404_MESSAGE = "Not Found";

    private static final String RESPONSE_405_MESSAGE = "Method not allowed";

    private static final String LEVEL_ERROR = "ERROR";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponseDTO handleGenericBadRequest(
            final RuntimeException exception) {
        final ErrorResponseDTO errorDto = new ErrorResponseDTO();
        errorDto.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorDto.setDescription(RESPONSE_400_MESSAGE);
        errorDto.setMessage(exception.getMessage());
        errorDto.setLevel(LEVEL_ERROR);
        log.error(exception.getMessage(), exception);
        return errorDto;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponseDTO handleHttpRequestMethodNotSupportedException(
            final HttpRequestMethodNotSupportedException exception) {
        final ErrorResponseDTO errorDto = new ErrorResponseDTO();
        errorDto.setCode(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()));
        errorDto.setDescription(RESPONSE_405_MESSAGE);
        errorDto.setMessage(exception.getMessage());
        errorDto.setLevel(LEVEL_ERROR);
        log.error(exception.getMessage(), exception);
        return errorDto;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponseDTO handleGenericException(
            final Exception exception) {
        final ErrorResponseDTO errorDto = new ErrorResponseDTO();
        errorDto.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        errorDto.setDescription(RESPONSE_500_MESSAGE);
        errorDto.setMessage(exception.getMessage());
        errorDto.setLevel(LEVEL_ERROR);
        log.error(exception.getMessage(), exception);
        return errorDto;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorResponseDTO handleMissingServletRequestParameterException(final MissingServletRequestParameterException exception) {
        final ErrorResponseDTO errorDto = new ErrorResponseDTO();
        errorDto.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorDto.setDescription(RESPONSE_400_MESSAGE);
        errorDto.setMessage(exception.getMessage());
        errorDto.setLevel(LEVEL_ERROR);
        log.error(exception.getMessage(), exception);
        return errorDto;
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RestClientException.class)
    public ErrorResponseDTO handleRestClientException(final RestClientException exception) {
        final ErrorResponseDTO errorDto = new ErrorResponseDTO();
        errorDto.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        errorDto.setDescription(RESPONSE_500_MESSAGE);
        errorDto.setMessage(exception.getMessage());
        errorDto.setLevel(LEVEL_ERROR);
        log.error(exception.getMessage(), exception);
        return errorDto;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorResponseDTO handleProductNotFound(final ProductNotFoundException exception) {
        final ErrorResponseDTO errorDto = new ErrorResponseDTO();
        errorDto.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorDto.setDescription(RESPONSE_404_MESSAGE);
        errorDto.setMessage(exception.getMessage());
        errorDto.setLevel(LEVEL_ERROR);
        log.error(exception.getMessage(), exception);
        return errorDto;
    }

}
