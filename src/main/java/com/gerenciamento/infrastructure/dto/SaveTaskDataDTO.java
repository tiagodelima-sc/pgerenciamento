package com.gerenciamento.infrastructure.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class SaveTaskDataDTO {

    @NotNull(message = "O título é obrigatório")
    private final String title;

    @NotNull(message = "A descrição é obrigatória")
    @Size(min = 5, max = 150, message = "A descrição deve ter entre 5 e 150 caracteres")
    private final String description;

    @NotNull(message = "O número de dias é obrigatório")
    @Positive(message = "O número deve ser positivo")
    private final Integer numberOfDays;

    private final String status;

    private final String projectId;

    private final String memberId;
}
