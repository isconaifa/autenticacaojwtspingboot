package br.com.clinicanaifa.security;

import br.com.clinicanaifa.entity.Usuario;
import br.com.clinicanaifa.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private final Usuario usuario;
    public UserDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getRoles().stream()
                .map(Role::getAuthority)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuario.isAtivo();
    }

    // Método utilitário para acessar o usuário original (útil nos controllers)
    public Usuario getUsuario() {
        return usuario;
    }

    // Verifica se tem role específica (opcional, mas útil)
    public boolean hasRole(Role role) {
        return usuario.getRoles().contains(role);
    }
}
