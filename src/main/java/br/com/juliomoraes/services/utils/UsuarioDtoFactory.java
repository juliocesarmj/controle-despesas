package br.com.juliomoraes.services.utils;

import br.com.juliomoraes.api.dtos.usuario.UsuarioResponseDto;
import br.com.juliomoraes.model.Usuario;

public class UsuarioDtoFactory {

    public static UsuarioResponseDto toDto(Usuario usuario) {
        return UsuarioResponseDto.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
    }
}
