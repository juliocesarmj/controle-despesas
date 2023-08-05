package br.com.juliomoraes.services.movimento;

import br.com.juliomoraes.api.dtos.MovimentoCriacaoDto;
import br.com.juliomoraes.model.Movimento;
import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.repositories.MovimentoRepository;
import br.com.juliomoraes.services.auth.AuthService;
import br.com.juliomoraes.services.exceptions.MovimentoNotFoundException;
import br.com.juliomoraes.services.utils.MovimentoFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovimentoServiceImpl implements MovimentoService {

    private final AuthService authService;
    private final MovimentoRepository movimentoRepository;

    public MovimentoServiceImpl(AuthService authService, MovimentoRepository movimentoRepository) {
        this.authService = authService;
        this.movimentoRepository = movimentoRepository;
    }

    @Override
    public Movimento novo(MovimentoCriacaoDto dto) {
        Movimento.MovimentoBuilder movimentoBuilder = MovimentoFactory.toEntity(dto);
        movimentoBuilder.usuario(authService.authenticated());
        return movimentoRepository.save(movimentoBuilder.build());
    }

    @Override
    public Page<Movimento> obterMovimentos(Pageable pageable) {
        Usuario authenticated = authService.authenticated();
        if (!authenticated.isAdmin())
            return movimentoRepository.findAllByUsuario(authenticated, pageable);
        return movimentoRepository.findAll(pageable);
    }

    @Override
    public Movimento obterPorId(Long id) {
        return movimentoRepository.findByIdAndUsuario(id, authService.authenticated())
                .orElseThrow(() -> new MovimentoNotFoundException(id));
    }

    @Override
    public List<Movimento> obterPorDatas(LocalDate dataVencimentoInicio, LocalDate dataVencimentoFim) {
        return null;
    }

    @Override
    public List<Movimento> obterMovimentosPagos() {
        return null;
    }

    @Override
    public List<Movimento> obterMovimentosAtrasados() {
        return null;
    }

    @Override
    public List<Movimento> obterMovimentosPendentes() {
        return null;
    }

    @Override
    public Movimento atualizar(Long id, MovimentoCriacaoDto dto) {
        Movimento movimento = this.obterPorId(id);
        BeanUtils.copyProperties(dto, movimento);
        movimentoRepository.save(movimento);
        return movimento;
    }

    @Override
    public Movimento excluir(Long id) {
        Movimento movimento = obterPorId(id);
        movimentoRepository.delete(movimento);
        return movimento;
    }
}
