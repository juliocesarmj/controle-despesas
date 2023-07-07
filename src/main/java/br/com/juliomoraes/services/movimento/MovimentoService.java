package br.com.juliomoraes.services.movimento;

import br.com.juliomoraes.model.Movimento;
import br.com.juliomoraes.model.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface MovimentoService {
    Movimento novo(Movimento movimento);
    List<Movimento> obterMovimentosUsuarioLogado(Usuario usuario);
    Movimento obterPorId(Long id, Usuario usuario);
    Movimento obterPorId(Long id);
    List<Movimento> obterPorDatas(LocalDate dataVencimentoInicio, LocalDate dataVencimentoFim, Usuario usuario);
    List<Movimento> obterMovimentosPagos(Usuario usuario);
    List<Movimento> obterMovimentosAtrasados(Usuario usuario);
    List<Movimento> obterMovimentosPendentes(Usuario usuario);
}
