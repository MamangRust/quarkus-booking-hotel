package org.sanedge.domain.exception;

public class EmailAlreadyExistsException extends BusinessException {
    public EmailAlreadyExistsException() {
        super(3, "email already exists");
    }
}
