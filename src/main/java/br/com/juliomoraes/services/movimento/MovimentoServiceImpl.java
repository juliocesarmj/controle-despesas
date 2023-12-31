package br.com.juliomoraes.services.movimento;

import br.com.juliomoraes.api.dtos.MovimentoCriacaoDto;
import br.com.juliomoraes.model.Movimento;
import br.com.juliomoraes.model.Usuario;
import br.com.juliomoraes.model.enums.TipoDespesa;
import br.com.juliomoraes.repositories.MovimentoRepository;
import br.com.juliomoraes.repositories.specifications.MovimentoSpecifications;
import br.com.juliomoraes.services.auth.AuthService;
import br.com.juliomoraes.services.exceptions.MovimentoNotFoundException;
import br.com.juliomoraes.services.utils.MovimentoFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public Page<Movimento> obterMovimentosComFiltros(TipoDespesa tipoDespesa, Boolean pago, LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        Usuario authenticated = authService.authenticated();

        Specification<Movimento> specification = Specification.where(
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.literal(1), 1));

            if (tipoDespesa != null) {
                specification = specification.and(MovimentoSpecifications.byTipoDespesa(tipoDespesa));
            }

            if (pago != null) {
                specification = specification.and(MovimentoSpecifications.byPago(pago));
            }

            if (dataInicio != null && dataFim != null) {
                specification = specification.and(MovimentoSpecifications.byDataBetween(dataInicio, dataFim));
            }

            if(!authenticated.isAdmin()) {
                specification = specification.and(MovimentoSpecifications.byUsuarioAutenticado(authenticated));
            }

        return movimentoRepository.findAll(specification, pageable);
    }

    @Override
    public Movimento obterPorId(Long id) {
        Usuario authenticated = authService.authenticated();
        if (!authenticated.isAdmin())
            return movimentoRepository.findByIdAndUsuario(id, authenticated)
                    .orElseThrow(() -> new MovimentoNotFoundException(id));
        return movimentoRepository.findById(id).orElseThrow(() -> new MovimentoNotFoundException(id));
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
