package br.com.juliomoraes.api.dtos.usuario;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDto {

    private String nome;
    private String email;
    private LocalDateTime dataCadastro;
    private boolean ativo;
}
