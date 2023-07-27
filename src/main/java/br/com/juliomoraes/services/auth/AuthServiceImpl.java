package br.com.juliomoraes.services.auth;

import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.repositories.UsuarioRepository;
import br.com.juliomoraes.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario authenticated() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String userName = jwtPrincipal.getClaim("username");
            return repository.obterPorEmail(userName)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não habilitado para realizar a operação"));
    }
}
