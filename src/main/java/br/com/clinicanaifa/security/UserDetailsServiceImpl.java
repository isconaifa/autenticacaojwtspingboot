package br.com.clinicanaifa.security;

import br.com.clinicanaifa.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository repository;
    public UserDetailsServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
