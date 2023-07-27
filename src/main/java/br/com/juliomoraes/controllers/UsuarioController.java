package br.com.juliomoraes.controllers;

import br.com.juliomoraes.dto.usuario.UsuarioRequestDto;
import br.com.juliomoraes.dto.usuario.UsuarioResponseDto;
import br.com.juliomoraes.services.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDto create(@RequestBody @Valid UsuarioRequestDto dto) {
        return usuarioService.criar(dto);
    }
}
