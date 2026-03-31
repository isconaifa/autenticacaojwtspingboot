package br.com.clinicanaifa.controller;

import br.com.clinicanaifa.dto.request.LoginRequestDTO;
import br.com.clinicanaifa.dto.response.LoginResponseDTO;
import br.com.clinicanaifa.entity.Usuario;
import br.com.clinicanaifa.enums.Role;
import br.com.clinicanaifa.exception.ResourceNotFoundException;
import br.com.clinicanaifa.repository.UsuarioRepository;
import br.com.clinicanaifa.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // LOGIN - usando seu LoginRequestDTO
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.senha()
                )
        );

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        String token = jwtService.generateToken(usuario.getEmail());
        return ResponseEntity.ok(new LoginResponseDTO(
                token,
                usuario.getEmail(),
                usuario.getRoles().stream().map(Role::name).collect(Collectors.toSet())
        ));
    }
}
