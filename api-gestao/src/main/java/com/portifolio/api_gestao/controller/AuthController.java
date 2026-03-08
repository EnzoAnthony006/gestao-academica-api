package com.portifolio.api_gestao.controller;

import com.portifolio.api_gestao.dto.LoginRequest;
import com.portifolio.api_gestao.dto.LoginResponse;
import com.portifolio.api_gestao.model.Usuario;
import com.portifolio.api_gestao.service.AuthService;
import com.portifolio.api_gestao.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UsuarioService usuarioService;

    public AuthController(AuthService authService, UsuarioService usuarioService) {
        this.authService = authService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.salvar(usuario));
    }
}