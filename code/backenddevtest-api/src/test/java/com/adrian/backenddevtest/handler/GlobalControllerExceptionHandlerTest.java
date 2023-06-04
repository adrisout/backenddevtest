package com.adrian.backenddevtest.handler;

import com.adrian.backenddevtest.exception.ProductNotFoundException;
import com.adrian.backenddevtest.exception.RestClientException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.openapitools.model.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class GlobalControllerExceptionHandlerTest {

    private static final String LEVEL_ERROR = "ERROR";

    private static final String ERROR_CODE_400 = "400";

    private static final String ERROR_CODE_404 = "404";

    private static final String ERROR_CODE_405 = "405";

    private static final String ERROR_CODE_500 = "500";

    @InjectMocks
    private GlobalControllerExceptionHandler globalControllerExceptionHandler;

    @Test
    void handleMissingServletRequestParameterException_WhenExceptionIsThrown_Return400BadRequest() {
        final MissingServletRequestParameterException missingServletRequestParameterException = new MissingServletRequestParameterException("brandId", "java.lang.Integer");
        final ErrorResponseDTO error = this.globalControllerExceptionHandler.handleMissingServletRequestParameterException(missingServletRequestParameterException);

        assertEquals(ERROR_CODE_400, error.getCode());
        assertEquals(LEVEL_ERROR, error.getLevel());
    }

    @Test
    void handleGenericBadRequest_WhenExceptionIsThrown_Return400BadRequest() {
        final RuntimeException runtimeException = new RuntimeException("Runtime exception test");
        final ErrorResponseDTO error = this.globalControllerExceptionHandler.handleGenericBadRequest(runtimeException);

        assertEquals(ERROR_CODE_400, error.getCode());
        assertEquals(LEVEL_ERROR, error.getLevel());
    }

    @Test
    void handleGenericException_WhenExceptionIsThrown_Return500InternalServerError() {
        final Exception exception = new Exception("Exception test");
        final ErrorResponseDTO error = this.globalControllerExceptionHandler.handleGenericException(exception);

        assertEquals(ERROR_CODE_500, error.getCode());
        assertEquals(LEVEL_ERROR, error.getLevel());
    }

    @Test
    void handleHttpRequestMethodNotSupportedException_WhenExceptionIsThrown_Return405MethodNotAllowed() {
        final HttpRequestMethodNotSupportedException exception = new HttpRequestMethodNotSupportedException("Exception test");
        final ErrorResponseDTO error = this.globalControllerExceptionHandler.handleHttpRequestMethodNotSupportedException(exception);

        assertEquals(ERROR_CODE_405, error.getCode());
        assertEquals(LEVEL_ERROR, error.getLevel());
    }


    @Test
    void handleRestClientException_WhenExceptionIsThrown_Return500InternalServerError() {
        final RestClientException restClientException = new RestClientException("Test");
        final ErrorResponseDTO errorResponseDTO = this.globalControllerExceptionHandler.handleRestClientException(restClientException);

        assertEquals(ERROR_CODE_500, errorResponseDTO.getCode());
        assertEquals(LEVEL_ERROR, errorResponseDTO.getLevel());
    }

    @Test
    void handleProductNotFoundException_WhenExceptionIsThrown_Return404NotFound() {
        final ProductNotFoundException productNotFoundException = new ProductNotFoundException("Test");
        final ErrorResponseDTO errorResponseDTO = this.globalControllerExceptionHandler.handleProductNotFound(productNotFoundException);

        assertEquals(ERROR_CODE_404, errorResponseDTO.getCode());
        assertEquals(LEVEL_ERROR, errorResponseDTO.getLevel());
    }
}