package br.com.juliomoraes.controllers.handlers;

import br.com.juliomoraes.controllers.exceptions.ErrorResponse;
import br.com.juliomoraes.services.exceptions.EntityExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UsuarioHandler {

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorResponse> handlerEntityExistsException(EntityExistsException e, HttpServletRequest req) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(badRequest).body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(badRequest.value())
                        .timestamp(LocalDateTime.now())
                        .path(req.getRequestURI())
                .build());
    }
}
