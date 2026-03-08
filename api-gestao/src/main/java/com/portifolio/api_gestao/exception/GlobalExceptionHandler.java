package com.portifolio.api_gestao.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroResponse> handleResourceNotFound(ResourceNotFoundException ex,
                                                               HttpServletRequest request) {
        ErroResponse erro = new ErroResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(erro ->
                erros.put(erro.getField(), erro.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroResponse> handleRuntimeException(RuntimeException ex,
                                                               HttpServletRequest request) {
        ErroResponse erro = new ErroResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenericException(Exception ex,
                                                               HttpServletRequest request) {
        ErroResponse erro = new ErroResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}