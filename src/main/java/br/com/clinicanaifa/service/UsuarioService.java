package br.com.clinicanaifa.service;

import br.com.clinicanaifa.dto.request.UsuarioRequestDTO;
import br.com.clinicanaifa.dto.response.UsuarioResponseDTO;
import br.com.clinicanaifa.entity.Usuario;
import br.com.clinicanaifa.exception.DuplicateResourceException;
import br.com.clinicanaifa.mapper.UsuarioMapper;
import br.com.clinicanaifa.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    public UsuarioService(UsuarioRepository usuarioRepository,  PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto){
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new DuplicateResourceException("Email já cadastrado");
        }
        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(usuarioSalvo);
    }
}
