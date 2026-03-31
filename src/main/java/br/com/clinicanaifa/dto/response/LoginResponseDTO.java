package br.com.clinicanaifa.dto.response;

import java.util.Set;

public record LoginResponseDTO(
        String token,
        String email,
        Set<String> roles
) {}