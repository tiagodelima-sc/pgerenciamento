package com.gerenciamento.infrastructure.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveProjectDataDTO {

    @NotNull(message = "O nome é obrigatório")
    @Size(min = 3, max = 80, message = "O nome deve ter entre 3 e 80 caracteres")
    private final String name;

    @NotNull(message = "A descrição é obrigatória")
    @Size(min = 5, max = 150, message = "A descrição deve ter entre 5 e 150 caracteres")
    private final String description;

    @NotNull(message = "A data inicial é obrigatória")
    private final LocalDate initialDate;

    @NotNull(message = "A data final é obrigatória")
    private final LocalDate finalDate;

    private final String status;
}
