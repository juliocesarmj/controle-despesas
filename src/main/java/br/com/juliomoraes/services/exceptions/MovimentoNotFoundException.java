package br.com.juliomoraes.services.exceptions;

public class MovimentoNotFoundException extends EntityNotFoundException {
    public MovimentoNotFoundException(String message) {
        super(message);
    }

    public MovimentoNotFoundException(Long movimentoId) {
        this(String.format("Não existe movimento cadastrado de código %d", movimentoId));
    }
}
