package br.com.clinicanaifa.entity;

import br.com.clinicanaifa.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String senha;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "role")
    private Set<Role> roles;
    private boolean ativo = true;
}
