package br.com.juliomoraes.services.usuario;

import br.com.juliomoraes.dto.usuario.UsuarioRequestDto;
import br.com.juliomoraes.dto.usuario.UsuarioResponseDto;
import br.com.juliomoraes.model.Perfil;
import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.model.enums.TipoPerfil;
import br.com.juliomoraes.repositories.PerfilRepository;
import br.com.juliomoraes.repositories.UsuarioRepository;
import br.com.juliomoraes.services.exceptions.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponseDto criar(UsuarioRequestDto dto) {
        Perfil perfil = this.perfilRepository.findByTipo(TipoPerfil.ROLE_FREE).orElseThrow(
                () -> new RuntimeException("Erro interno no servidor. Contate o administrador"));

        this.validarEmailExistente(dto.getEmail());

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .perfil(perfil)
                .build();
        this.usuarioRepository.save(usuario);
        return UsuarioResponseDto
                .builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .dataCadastro(usuario.getDataCadastro())
                .ativo(usuario.isAtivo())
                .build();
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
        return usuarioRepository.findByEmail(username);
    }
}
