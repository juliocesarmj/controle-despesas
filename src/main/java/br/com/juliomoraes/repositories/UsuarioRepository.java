package br.com.juliomoraes.repositories;

import br.com.juliomoraes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("from Usuario u where u.email = :email")
    Optional<Usuario> obterPorEmail(String email);
    UserDetails findByEmail(String email);
}
