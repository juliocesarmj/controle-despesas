package br.com.juliomoraes.services.exceptions;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
