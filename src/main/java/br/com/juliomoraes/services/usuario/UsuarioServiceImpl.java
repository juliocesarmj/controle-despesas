package br.com.juliomoraes.services.usuario;

import br.com.juliomoraes.dto.usuario.UsuarioRequestDto;
import br.com.juliomoraes.dto.usuario.UsuarioResponseDto;
import br.com.juliomoraes.model.Perfil;
import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.model.enums.TipoPerfil;
import br.com.juliomoraes.projection.UserDetailsProjection;
import br.com.juliomoraes.repositories.PerfilRepository;
import br.com.juliomoraes.repositories.UsuarioRepository;
import br.com.juliomoraes.services.exceptions.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public UsuarioResponseDto obterInfo() {
        return null;
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
