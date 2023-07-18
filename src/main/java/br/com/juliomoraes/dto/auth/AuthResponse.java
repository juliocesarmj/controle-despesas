package br.com.juliomoraes.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String nome;
    private String email;
    private LocalDateTime expiracao;

    private String accessToken;

    private String permissao;
}
