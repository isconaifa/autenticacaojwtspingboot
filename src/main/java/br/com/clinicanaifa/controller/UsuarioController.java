package br.com.clinicanaifa.controller;


import br.com.clinicanaifa.dto.request.UsuarioRequestDTO;
import br.com.clinicanaifa.dto.response.UsuarioResponseDTO;
import br.com.clinicanaifa.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar( @RequestBody @Valid UsuarioRequestDTO dto) {
        return ResponseEntity.ok(usuarioService.salvar(dto));
    }
}
