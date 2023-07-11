package br.com.juliomoraes.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String nome;
    private String email;
    private Instant expiracao;

    private String accessToken;

    private String permissao;
}
