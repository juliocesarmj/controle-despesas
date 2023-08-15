package br.com.juliomoraes.services.usuario.impl;

import br.com.juliomoraes.api.dtos.usuario.UsuarioRequestPutDto;
import br.com.juliomoraes.api.dtos.usuario.UsuarioResponseDto;
import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.repositories.UsuarioRepository;
import br.com.juliomoraes.services.usuario.interfaces.UsuarioAtualizacaoService;
import br.com.juliomoraes.services.utils.UsuarioDtoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAtualizacaoServiceImpl implements UsuarioAtualizacaoService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioAtualizacaoServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UsuarioResponseDto atualizarUsuario(Usuario usuario, UsuarioRequestPutDto dto) {
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setNome(dto.getNome());

        return UsuarioDtoFactory.toDto(usuarioRepository.save(usuario));
    }
}
