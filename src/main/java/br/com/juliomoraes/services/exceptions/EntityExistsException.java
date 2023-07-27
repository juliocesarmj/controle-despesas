package br.com.juliomoraes.services.exceptions;

public class EntityExistsException extends BusinessException {

    public EntityExistsException(String message) {
        super(message);
    }
}
