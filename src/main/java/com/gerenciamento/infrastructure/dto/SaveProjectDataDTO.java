package com.gerenciamento.infrastructure.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@SuppressWarnings("unused")
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

    private final Set<String> memberIds;

    @AssertTrue(message = "A data final deve ser posterior à data inicial")
    private boolean isInitialDateBeforeFinalDate() {
        if (initialDate == null || finalDate == null)
            return true;

        return finalDate.isAfter(initialDate);
    }

    @AssertTrue(message = "A data inicial não pode estar no passado")
    private boolean isInitialDateValid() {
        if (initialDate == null)
            return true;

        return !initialDate.isBefore(LocalDate.now());
    }

    @AssertTrue(message = "Nome e descrição não podem ser iguais")
    private boolean isNameDifferentFromDescription() {
        if (name == null || description == null)
            return true;

        return !name.equalsIgnoreCase(description);
    }
}
