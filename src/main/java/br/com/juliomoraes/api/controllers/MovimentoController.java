package br.com.juliomoraes.api.controllers;

import br.com.juliomoraes.api.dtos.MovimentoCriacaoDto;
import br.com.juliomoraes.model.Movimento;
import br.com.juliomoraes.services.exceptions.BusinessException;
import br.com.juliomoraes.services.exceptions.UserNotFoundException;
import br.com.juliomoraes.services.movimento.MovimentoService;
import jakarta.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Movimento> findAll() {
        try {
            return movimentoService.obterMovimentosUsuarioLogado();
        } catch (UserNotFoundException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new BusinessException(e.getMessage(), rootCause);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREE', 'ROLE_PREMIUM')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Movimento criar(@RequestBody @Valid MovimentoCriacaoDto dto) {
        try {
            return movimentoService.novo(dto);
        } catch (UserNotFoundException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new BusinessException(e.getMessage(), rootCause);
        }
    }
}
