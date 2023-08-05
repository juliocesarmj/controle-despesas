package br.com.juliomoraes.repositories;

import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.projection.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("from Usuario u where u.email = :email")
    Optional<Usuario> obterPorEmail(String email);
    @Query(nativeQuery = true, value = """
				SELECT tb_usuario.email AS username, tb_usuario.senha as password, tb_perfil.id AS roleId, tb_perfil.tipo as authority
				FROM tb_usuario
				INNER JOIN tb_usuario_perfil ON tb_usuario.id = tb_usuario_perfil.usuario_id
				INNER JOIN tb_perfil ON tb_perfil.id = tb_usuario_perfil.perfil_id
				WHERE tb_usuario.email = :email
				AND tb_usuario.ativo = true
			""")
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
}
