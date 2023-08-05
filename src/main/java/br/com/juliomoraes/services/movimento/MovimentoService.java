package br.com.juliomoraes.services.movimento;

import br.com.juliomoraes.api.dtos.MovimentoCriacaoDto;
import br.com.juliomoraes.model.Movimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface MovimentoService {
    Movimento novo(MovimentoCriacaoDto dto);

    Page<Movimento> obterMovimentos(Pageable pageable);

    Movimento obterPorId(Long id);

    List<Movimento> obterPorDatas(LocalDate dataVencimentoInicio, LocalDate dataVencimentoFim);

    List<Movimento> obterMovimentosPagos();

    List<Movimento> obterMovimentosAtrasados();

    List<Movimento> obterMovimentosPendentes();

    Movimento atualizar(Long id, MovimentoCriacaoDto dto);

    Movimento excluir(Long id);
}
