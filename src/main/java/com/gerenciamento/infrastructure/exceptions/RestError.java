package com.gerenciamento.infrastructure.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RestError {
    private final String errorCode;
    private final String errorMessage;
    private final int status;
    private final String path;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
