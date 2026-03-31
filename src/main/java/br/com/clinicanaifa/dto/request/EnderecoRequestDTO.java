package br.com.clinicanaifa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoRequestDTO(

        @NotBlank
        String bairro,

        @NotBlank
        String logradouro,

        @NotBlank
        String complemento,

        @NotBlank
        @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "CEP deve estar no formato XXXXX-XXX")
        String cep,

        String numero,

        @NotBlank
        String cidade,

        @NotBlank
        @Pattern(regexp = "^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$")
        String uf

) {}