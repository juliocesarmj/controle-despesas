package br.com.juliomoraes.services.usuario;

import br.com.juliomoraes.dto.usuario.UsuarioRequestDto;
import br.com.juliomoraes.dto.usuario.UsuarioResponseDto;
import br.com.juliomoraes.model.Perfil;
import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.model.enums.TipoPerfil;
import br.com.juliomoraes.repositories.PerfilRepository;
import br.com.juliomoraes.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;


    @Override
    public UsuarioResponseDto criar(UsuarioRequestDto dto) {
        Perfil perfil = this.perfilRepository.findByTipo(TipoPerfil.FREE).orElseThrow(
                () -> new RuntimeException("Erro interno no servidor. Contate o administrador"));

        usuarioRepository
                .findByEmail(dto.getEmail())
                .ifPresent(usuario -> new RuntimeException("User not able: " + dto.getEmail()));

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
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

}
