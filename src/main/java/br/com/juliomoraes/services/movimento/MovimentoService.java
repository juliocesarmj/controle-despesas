package br.com.juliomoraes.services.movimento;

import br.com.juliomoraes.api.dtos.MovimentoCriacaoDto;
import br.com.juliomoraes.model.Movimento;
import br.com.juliomoraes.model.enums.TipoDespesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface MovimentoService {
    Movimento novo(MovimentoCriacaoDto dto);

    Page<Movimento> obterMovimentos(Pageable pageable);

    Page<Movimento> obterMovimentosComFiltros(TipoDespesa tipoDespesa, Boolean pago, LocalDate dataInicio, LocalDate dataFim, Pageable pageable);

    Movimento obterPorId(Long id);

    Movimento atualizar(Long id, MovimentoCriacaoDto dto);

    Movimento excluir(Long id);
}
