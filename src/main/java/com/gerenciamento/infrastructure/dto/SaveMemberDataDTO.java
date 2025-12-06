package com.gerenciamento.infrastructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SaveMemberDataDTO {

    @NotNull(message = "O nome é obrigatório")
    @Size(min = 3, max = 80, message = "O nome deve ter entre 3 e 80 caracteres")
    private final String name;

    @NotNull(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail não é válido")
    private final String email;
}
