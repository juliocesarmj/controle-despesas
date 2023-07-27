package br.com.juliomoraes.services.movimento;

import br.com.juliomoraes.model.Movimento;
import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.repositories.MovimentoRepository;
import br.com.juliomoraes.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovimentoServiceImpl implements MovimentoService {

    @Autowired
    private AuthService authService;

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Override
    public Movimento novo(Movimento movimento) {
        return null;
    }

    @Override
    public List<Movimento> obterMovimentosUsuarioLogado() {
        return movimentoRepository.findAllByUsuario(authService.authenticated());
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
