package br.com.juliomoraes.services.usuario.interfaces;

import br.com.juliomoraes.api.dtos.usuario.UsuarioRequestPutDto;
import br.com.juliomoraes.api.dtos.usuario.UsuarioResponseDto;
import br.com.juliomoraes.model.Usuario;

public interface UsuarioAtualizacaoService {

    UsuarioResponseDto atualizarUsuario(Usuario usuario, UsuarioRequestPutDto dto);
}
