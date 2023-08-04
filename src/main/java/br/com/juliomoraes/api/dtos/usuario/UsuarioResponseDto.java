package br.com.juliomoraes.api.dtos.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDto {

    private String nome;
    private String email;
    private LocalDateTime dataCadastro;
    private Boolean ativo;
}
