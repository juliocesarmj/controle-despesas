package br.com.juliomoraes.services.usuario;

import br.com.juliomoraes.dto.usuario.UsuarioRequestDto;
import br.com.juliomoraes.dto.usuario.UsuarioResponseDto;

public interface UsuarioService {

    UsuarioResponseDto criar(UsuarioRequestDto dto);

    UsuarioResponseDto obterInfo();

}
