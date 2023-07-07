package br.com.juliomoraes.repositories;

import br.com.juliomoraes.model.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
}
