package br.com.juliomoraes.services.exceptions;

public class UserNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1L;
    public UserNotFoundException(String message) {
        super(message);
    }

}
