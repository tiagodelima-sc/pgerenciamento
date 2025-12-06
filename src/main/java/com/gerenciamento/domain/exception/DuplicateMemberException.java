package com.gerenciamento.domain.exception;

import com.gerenciamento.infrastructure.exception.RequestException;

public class DuplicateMemberException extends RequestException {

    public DuplicateMemberException(String emailMember) {
        super("MemberDuplicate", "Já existe algum membro com esse email: " + emailMember);
    }
}
