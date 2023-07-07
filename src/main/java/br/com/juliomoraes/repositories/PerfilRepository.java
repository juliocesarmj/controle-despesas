package br.com.juliomoraes.repositories;

import br.com.juliomoraes.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
