package com.gs.energy.auth.controller;

import com.gs.energy.config.JwtUtil;
import com.gs.energy.auth.dto.AuthRequest;
import com.gs.energy.auth.dto.AuthResponse;
import com.gs.energy.auth.model.Role;
import com.gs.energy.auth.model.User;
import com.gs.energy.auth.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserRepository userRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // login -> retorna token JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        var userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }

        String token = jwtUtil.generateToken(userOpt.get());
        AuthResponse resp = new AuthResponse(token, request.getUsername());
        return ResponseEntity.ok(resp);
    }

    // registro simples (gera um usuário ROLE_MANAGER por padrão)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username já existe");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(Role.ROLE_MANAGER)); // padrão
        userRepository.save(user);
        return ResponseEntity.ok("Usuário criado");
    }
}
