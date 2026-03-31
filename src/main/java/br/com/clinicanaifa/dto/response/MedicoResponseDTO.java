package br.com.clinicanaifa.dto.response;

import br.com.clinicanaifa.enums.Especialidade;

public record MedicoResponseDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        EnderecoResponseDTO endereco
) {}