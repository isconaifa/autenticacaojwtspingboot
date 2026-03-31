package br.com.clinicanaifa.dto.request;

import br.com.clinicanaifa.enums.Role;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record UsuarioRequestDTO(
        @NotBlank String email,
        @NotBlank String senha,
        Set<Role> roles  // Opcional: se null, o mapper assume USER como padrão
) {
}