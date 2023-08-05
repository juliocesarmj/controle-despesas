package br.com.juliomoraes.api.controllers.handlers;

import br.com.juliomoraes.api.controllers.exceptions.ErrorResponse;
import br.com.juliomoraes.services.exceptions.BusinessException;
import br.com.juliomoraes.services.exceptions.EntityExistsException;
import br.com.juliomoraes.services.exceptions.EntityNotFoundException;
import br.com.juliomoraes.services.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ProblemType problemType = ProblemType.USUARIO_TOKEN_NAO_ENCONTRADO;
        String detail = ex.getMessage();

        ErrorResponse errorResponse = createErrorResponseBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        String detail = ex.getMessage();

        ErrorResponse errorResponse = createErrorResponseBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<?> handlerEntityExistsException(EntityExistsException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        String detail = ex.getMessage();

        ErrorResponse errorResponse = createErrorResponseBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = ex.getMessage();

        ErrorResponse errorResponse = createErrorResponseBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        HttpStatus status = getStatus(statusCode);
        if (body == null) {
            body = ErrorResponse.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = ErrorResponse.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ErrorResponse.ErrorResponseBuilder createErrorResponseBuilder(HttpStatus status,
                                                        ProblemType problemType, String detail) {

        return ErrorResponse.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }
    private static HttpStatus getStatus(HttpStatusCode statusCode) {
        return (HttpStatus) statusCode;
    }
}
