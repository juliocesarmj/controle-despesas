package br.com.juliomoraes.repositories;

import br.com.juliomoraes.model.Movimento;
import br.com.juliomoraes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

    List<Movimento> findAllByUsuario(Usuario usuario);

    Optional<Movimento> findByIdAndUsuario(Long id, Usuario usuario);
}
