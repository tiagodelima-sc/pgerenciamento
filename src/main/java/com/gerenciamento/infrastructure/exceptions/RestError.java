package com.gerenciamento.infrastructure.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class RestError {
    private final String errorCode;
    private final String errorMessage;
    private final List<String> details;
    private final int status;
    private final String path;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
