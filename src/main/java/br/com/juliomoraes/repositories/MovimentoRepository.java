package br.com.juliomoraes.repositories;

import br.com.juliomoraes.model.Movimento;
import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.repositories.custom.CustomJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface MovimentoRepository extends CustomJpaRepository<Movimento, Long> {

    Page<Movimento> findAllByUsuario(Usuario usuario, Pageable pageable);

    Optional<Movimento> findByIdAndUsuario(Long id, Usuario usuario);
}
