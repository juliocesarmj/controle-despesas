package br.com.juliomoraes.repositories;

import br.com.juliomoraes.model.Perfil;
import br.com.juliomoraes.model.enums.TipoPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Optional<Perfil> findByTipo(TipoPerfil tipoPerfil);
}
