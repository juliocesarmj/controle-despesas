package br.com.juliomoraes.api.controllers;

import br.com.juliomoraes.api.dtos.usuario.UsuarioRequestDto;
import br.com.juliomoraes.api.dtos.usuario.UsuarioResponseDto;
import br.com.juliomoraes.services.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
}
