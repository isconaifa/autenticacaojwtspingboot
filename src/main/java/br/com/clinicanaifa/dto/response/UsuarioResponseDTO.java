package br.com.clinicanaifa.dto.response;

import java.util.Set;

public record UsuarioResponseDTO(
        Long id,
        String email,
        boolean ativo,
        Set<String> roles
) {
}