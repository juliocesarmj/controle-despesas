package br.com.juliomoraes.services.auth;

import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.services.exceptions.EntityNotFoundException;

public interface AuthService {

    Usuario authenticated() throws EntityNotFoundException;
}
