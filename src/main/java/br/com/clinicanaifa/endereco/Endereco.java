package br.com.clinicanaifa.endereco;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {
    private String bairro;
    private String logradouro;
    private String complemento;
    private String cep;
    private String numero;
    private String cidade;
    private String uf;
}
