package com.gerenciamento.domain.exception;

import com.gerenciamento.infrastructure.exception.RequestException;

public class DuplicateProjectException extends RequestException {

    public DuplicateProjectException(String nameProject) {
        super("ProjectDuplicate", "Já existe algum projeto com esse nome: " + nameProject);
    }
}
