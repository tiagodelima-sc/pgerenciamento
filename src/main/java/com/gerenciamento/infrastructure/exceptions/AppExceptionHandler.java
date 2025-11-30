package com.gerenciamento.infrastructure.exceptions;

import org.jspecify.annotations.Nullable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<Object> handlerRequestException(RequestException requestException, WebRequest request) {
        return handlerException(requestException, requestException.getErrorCode(), requestException.getMessage(), null, BAD_REQUEST, request);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handlerGenericException(Exception exception, WebRequest request) {
        return handlerException(exception, null, exception.getMessage(), null, INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<String> details = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .filter(Objects::nonNull)
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return handlerException(ex, "Erro de Validação", null, details, BAD_REQUEST, request);
    }

    private ResponseEntity<Object> handlerException(
            Exception exception,
            String errorCode,
            String errorMessage,
            List<String> details,
            HttpStatus httpStatus,
            WebRequest request
    ) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        return handleExceptionInternal(
                exception,
                RestError
                        .builder()
                        .errorCode(errorCode)
                        .errorMessage(errorMessage)
                        .details(details)
                        .status(httpStatus.value())
                        .path(servletWebRequest.getRequest().getRequestURI())
                        .build(),
                new HttpHeaders(),
                httpStatus,
                request
        );
    }
}
