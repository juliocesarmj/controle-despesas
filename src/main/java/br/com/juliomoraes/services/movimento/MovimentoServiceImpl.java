package br.com.juliomoraes.services.movimento;

import br.com.juliomoraes.model.Movimento;
import br.com.juliomoraes.model.Usuario;

import java.time.LocalDate;
import java.util.List;

public class MovimentoServiceImpl implements MovimentoService {
    @Override
    public Movimento novo(Movimento movimento) {
        return null;
    }

    @Override
    public List<Movimento> obterMovimentosUsuarioLogado(Usuario usuario) {
        return null;
    }

    @Override
    public Movimento obterPorId(Long id, Usuario usuario) {
        return null;
    }

    @Override
    public Movimento obterPorId(Long id) {
        return null;
    }

    @Override
    public List<Movimento> obterPorDatas(LocalDate dataVencimentoInicio, LocalDate dataVencimentoFim, Usuario usuario) {
        return null;
    }

    @Override
    public List<Movimento> obterMovimentosPagos(Usuario usuario) {
        return null;
    }

    @Override
    public List<Movimento> obterMovimentosAtrasados(Usuario usuario) {
        return null;
    }

    @Override
    public List<Movimento> obterMovimentosPendentes(Usuario usuario) {
        return null;
    }
}
