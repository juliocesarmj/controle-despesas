package br.com.juliomoraes.controllers;

import br.com.juliomoraes.model.Movimento;
import br.com.juliomoraes.services.movimento.MovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movimento")
public class MovimentoController {

    @Autowired
    private MovimentoService movimentoService;

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREE')")
    @GetMapping
    public List<Movimento> findAll() {
        return movimentoService.obterMovimentosUsuarioLogado();
    }
}
