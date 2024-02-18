package org.sanedge.domain.exception;

public class UsernameAlreadyExistsException extends BusinessException {
    public UsernameAlreadyExistsException() {
        super(2, "username already exists");
    }
}
