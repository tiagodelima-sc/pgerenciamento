package com.gerenciamento.infrastructure.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<Object> handlerRequestException(RequestException requestException, WebRequest request) {
        return handlerException(requestException, requestException.getErrorCode(), requestException.getMessage(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handlerGenericException(Exception exception, WebRequest request) {
        return handlerException(exception, null, exception.getMessage(), INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<Object> handlerException(
            Exception exception,
            String errorCode,
            String errorMessage,
            HttpStatus httpStatus,
            WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        return handleExceptionInternal(
                exception,
                RestError
                        .builder()
                        .errorCode(errorCode)
                        .errorMessage(errorMessage)
                        .status(httpStatus.value())
                        .path(servletWebRequest.getRequest().getRequestURI())
                        .build(),
                new HttpHeaders(),
                httpStatus,
                request
        );
    }
}
