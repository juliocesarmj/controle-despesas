package br.com.juliomoraes.services.usuario.interfaces;

import br.com.juliomoraes.api.dtos.usuario.UsuarioRequestDto;
import br.com.juliomoraes.api.dtos.usuario.UsuarioRequestPutDto;
import br.com.juliomoraes.api.dtos.usuario.UsuarioResponseDto;

public interface UsuarioService {

    UsuarioResponseDto criar(UsuarioRequestDto dto);
    UsuarioResponseDto atualizar(UsuarioRequestPutDto dto);
    UsuarioResponseDto obterInfo();

}
