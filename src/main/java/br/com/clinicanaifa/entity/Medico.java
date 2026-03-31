package br.com.clinicanaifa.entity;

import br.com.clinicanaifa.endereco.Endereco;
import br.com.clinicanaifa.enums.Especialidade;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Table(name = "medicos")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    @Email
    private String email;
    @Column(unique = true)
    private String crm;
    @Column(unique = true)
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
}
