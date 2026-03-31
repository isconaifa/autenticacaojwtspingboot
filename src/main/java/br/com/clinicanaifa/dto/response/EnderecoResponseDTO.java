package br.com.clinicanaifa.dto.response;

public record EnderecoResponseDTO(
        String bairro,
        String logradouro,
        String complemento,
        String cep,
        String numero,
        String cidade,
        String uf
) {}