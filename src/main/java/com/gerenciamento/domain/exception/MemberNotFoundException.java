package com.gerenciamento.domain.exception;

import com.gerenciamento.infrastructure.exception.RequestException;

public class MemberNotFoundException extends RequestException {

    public MemberNotFoundException(String memberId) {
        super("MemberNotFound", "Membro nao encontrado: " + memberId);
    }
}
