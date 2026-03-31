package br.com.clinicanaifa.mapper;

import br.com.clinicanaifa.dto.request.*;
import br.com.clinicanaifa.dto.response.*;
import br.com.clinicanaifa.entity.Medico;
import br.com.clinicanaifa.endereco.Endereco;

public class MedicoMapper {

    // DTO → Entity
    public static Medico toEntity(MedicoRequestDTO dto) {
        return new Medico(
                null,
                dto.nome(),
                dto.email(),
                dto.crm(),
                dto.telefone(),
                dto.especialidade(),
                toEndereco(dto.endereco())
        );
    }

    // Entity → DTO
    public static MedicoResponseDTO toDTO(Medico medico) {
        return new MedicoResponseDTO(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEspecialidade(),
                toEnderecoDTO(medico.getEndereco())
        );
    }

    // Endereco DTO → Entity
    private static Endereco toEndereco(EnderecoRequestDTO dto) {
        return new Endereco(
                dto.bairro(),
                dto.logradouro(),
                dto.complemento(),
                dto.cep(),
                dto.numero(),
                dto.cidade(),
                dto.uf()
        );
    }

    // Endereco Entity → DTO
    private static EnderecoResponseDTO toEnderecoDTO(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getBairro(),
                endereco.getLogradouro(),
                endereco.getComplemento(),
                endereco.getCep(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getUf()
        );
    }
}