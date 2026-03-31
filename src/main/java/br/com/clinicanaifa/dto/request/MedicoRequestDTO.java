package br.com.clinicanaifa.dto.request;

import br.com.clinicanaifa.enums.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record MedicoRequestDTO(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "^\\d{4,6}[\\-/\\s]?(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$")
        String crm,

        @NotBlank
        @Pattern(regexp = "^\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}$")
        String telefone,

        @NotNull
        Especialidade especialidade,

        @Valid
        @NotNull
        EnderecoRequestDTO endereco
) {}