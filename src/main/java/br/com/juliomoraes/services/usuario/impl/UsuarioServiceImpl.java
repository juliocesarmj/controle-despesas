package br.com.juliomoraes.services.usuario.impl;

import br.com.juliomoraes.api.dtos.usuario.UsuarioRequestDto;
import br.com.juliomoraes.api.dtos.usuario.UsuarioRequestPutDto;
import br.com.juliomoraes.api.dtos.usuario.UsuarioResponseDto;
import br.com.juliomoraes.model.Perfil;
import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.model.enums.TipoPerfil;
import br.com.juliomoraes.projection.UserDetailsProjection;
import br.com.juliomoraes.repositories.PerfilRepository;
import br.com.juliomoraes.repositories.UsuarioRepository;
import br.com.juliomoraes.services.auth.AuthService;
import br.com.juliomoraes.services.exceptions.EntityExistsException;
import br.com.juliomoraes.services.usuario.interfaces.UsuarioAtualizacaoService;
import br.com.juliomoraes.services.usuario.interfaces.UsuarioService;
import br.com.juliomoraes.services.utils.UsuarioDtoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final UsuarioAtualizacaoService usuarioAtualizacaoService;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              PerfilRepository perfilRepository,
                              PasswordEncoder passwordEncoder,
                              AuthService authService,
                              UsuarioAtualizacaoService usuarioAtualizacaoService) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
        this.usuarioAtualizacaoService = usuarioAtualizacaoService;
    }

    @Override
    public UsuarioResponseDto criar(UsuarioRequestDto dto) {
        Perfil perfil = this.perfilRepository.findByTipo(TipoPerfil.ROLE_FREE).orElseThrow(
                () -> new RuntimeException("Erro interno no servidor. Contate o administrador"));

        this.validarEmailExistente(dto.getEmail());

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.addPerfil(perfil);
        this.usuarioRepository.save(usuario);
        return UsuarioResponseDto
                .builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .dataCadastro(usuario.getDataCadastro())
                .ativo(usuario.isAtivo())
                .build();
    }

    @Override
    public UsuarioResponseDto atualizar(UsuarioRequestPutDto dto) {
        Usuario usuario = this.authService.authenticated();

        return usuarioAtualizacaoService.atualizarUsuario(usuario, dto);
    }

    @Override
    public UsuarioResponseDto obterInfo() {
        return UsuarioDtoFactory.toDto(authService.authenticated());
    }

    private void validarEmailExistente(String email) {
        usuarioRepository
                .obterPorEmail(email)
                .ifPresent(usuario -> {
                    throw new EntityExistsException("User not able: " + email);
                });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = usuarioRepository.searchUserAndRolesByEmail(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("Email not found");
        }

        Usuario user = new Usuario();
        user.setEmail(result.get(0).getUsername());
        user.setSenha(result.get(0).getPassword());
        for (UserDetailsProjection projection : result) {
            user.addPerfil(new Perfil(projection.getRoleId(), TipoPerfil.valueOf(projection.getAuthority())));
        }

        return user;
    }
}
