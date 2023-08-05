package br.com.juliomoraes.api.controllers;

import br.com.juliomoraes.api.dtos.MovimentoCriacaoDto;
import br.com.juliomoraes.model.Movimento;
import br.com.juliomoraes.services.exceptions.UserNotFoundException;
import br.com.juliomoraes.services.movimento.MovimentoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimento")
public class MovimentoController {

    private final MovimentoService movimentoService;
    public MovimentoController(MovimentoService movimentoService) {
        this.movimentoService = movimentoService;
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREE', 'ROLE_PREMIUM')")
    @GetMapping
    public Page<Movimento> findAll(
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
            Pageable pageable) {
        return movimentoService.obterMovimentos(pageable);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREE', 'ROLE_PREMIUM')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Movimento create(@RequestBody @Valid MovimentoCriacaoDto dto) throws UserNotFoundException {
        return movimentoService.novo(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREE', 'ROLE_PREMIUM')")
    @GetMapping("/{id}")
    public Movimento findById(@PathVariable("id") Long id) {
        return movimentoService.obterPorId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREE', 'ROLE_PREMIUM')")
    @PutMapping("/{id}")
    public Movimento update(@PathVariable("id") Long id, @RequestBody @Valid MovimentoCriacaoDto dto) {
        return movimentoService.atualizar(id, dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREE', 'ROLE_PREMIUM')")
    @DeleteMapping("/{id}")
    public Movimento delete(@PathVariable("id") Long id) {
        return movimentoService.excluir(id);
    }
}
