package br.com.juliomoraes.api.controllers;

import br.com.juliomoraes.api.dtos.MovimentoCriacaoDto;
import br.com.juliomoraes.model.Movimento;
import br.com.juliomoraes.services.movimento.MovimentoService;
import jakarta.validation.Valid;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREE')")
    @GetMapping
    public List<Movimento> findAll() {
        return movimentoService.obterMovimentosUsuarioLogado();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Movimento criar(@RequestBody @Valid MovimentoCriacaoDto dto) {
        return null;
    }
}
