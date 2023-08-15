package br.com.juliomoraes.api.controllers;

import br.com.juliomoraes.api.dtos.usuario.UsuarioRequestDto;
import br.com.juliomoraes.api.dtos.usuario.UsuarioRequestPutDto;
import br.com.juliomoraes.api.dtos.usuario.UsuarioResponseDto;
import br.com.juliomoraes.services.usuario.interfaces.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDto create(@RequestBody @Valid UsuarioRequestDto dto) {
        return usuarioService.criar(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREE', 'ROLE_PREMIUM')")
    @GetMapping
    public UsuarioResponseDto getInfo() {
        return usuarioService.obterInfo();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREE', 'ROLE_PREMIUM')")
    @PutMapping
    public UsuarioResponseDto update(@RequestBody @Valid UsuarioRequestPutDto dto) {
        return usuarioService.atualizar(dto);
    }

}
