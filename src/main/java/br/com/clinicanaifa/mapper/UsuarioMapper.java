package br.com.clinicanaifa.mapper;
import br.com.clinicanaifa.dto.request.UsuarioRequestDTO;
import br.com.clinicanaifa.dto.response.UsuarioResponseDTO;
import br.com.clinicanaifa.entity.Usuario;
import br.com.clinicanaifa.enums.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.email());
        // Se não vier roles no DTO, assume USER como padrão
        if (dto.roles() == null || dto.roles().isEmpty()) {
            usuario.setRoles(Set.of(Role.USER));
        } else {
            usuario.setRoles(dto.roles());
        }

        usuario.setAtivo(true);
        return usuario;
    }

    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.isAtivo(),
                usuario.getRoles() != null ?
                        usuario.getRoles().stream().map(Role::name).collect(Collectors.toSet())
                        : Set.of()
        );
    }
}
