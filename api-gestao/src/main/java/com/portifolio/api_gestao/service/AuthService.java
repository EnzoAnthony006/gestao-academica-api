package com.portifolio.api_gestao.service;

import com.portifolio.api_gestao.dto.LoginRequest;
import com.portifolio.api_gestao.dto.LoginResponse;
import com.portifolio.api_gestao.model.Usuario;
import com.portifolio.api_gestao.repository.UsuarioRepository;
import com.portifolio.api_gestao.security.JWTCreator;
import com.portifolio.api_gestao.security.JWTObject;
import com.portifolio.api_gestao.security.SecurityConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SecurityConfig securityConfig;

    public AuthService(UsuarioRepository usuarioRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       SecurityConfig securityConfig) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.securityConfig = securityConfig;
    }

    public LoginResponse login(LoginRequest request){

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("E-mail ou senha inválidos"));


        if(!passwordEncoder.matches(request.getSenha(), usuario.getSenha())){
            throw new RuntimeException("E-mail ou senha inválidos");
        }

        JWTObject jwtObject = new JWTObject();

        jwtObject.setSubject(usuario.getEmail());
        jwtObject.setIssuedAt(new Date());
        jwtObject.setExpiration(new Date(System.currentTimeMillis() + securityConfig.getExpiration()));


        jwtObject.setRoles(usuario.getRole().name().replace("ROLE_", ""));

        String token = JWTCreator.create(
                securityConfig.getPrefix(),
                securityConfig.getKey(),
                jwtObject
        );

        return new LoginResponse(token);
    }
}