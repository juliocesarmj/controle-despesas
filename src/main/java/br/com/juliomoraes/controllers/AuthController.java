package br.com.juliomoraes.controllers;

import br.com.juliomoraes.dto.auth.AuthRequest;
import br.com.juliomoraes.dto.auth.AuthResponse;
import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.services.auth.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid AuthRequest authRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password());
        var autenticacao = (Usuario)  manager.authenticate(token).getPrincipal();
        return ResponseEntity.ok().body(AuthResponse.builder()
                        .nome(autenticacao.getNome())
                        .email(autenticacao.getEmail())
                        .accessToken(tokenService.gerarToken(autenticacao))
                        .expiracao(TokenService.getExpirationAt())
                        .permissao(autenticacao.getPerfil().getTipo().name())
                .build());
    }
}
