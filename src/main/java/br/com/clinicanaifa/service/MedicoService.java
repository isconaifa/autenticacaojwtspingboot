package br.com.clinicanaifa.service;
import br.com.clinicanaifa.dto.request.MedicoRequestDTO;
import br.com.clinicanaifa.dto.response.MedicoResponseDTO;
import br.com.clinicanaifa.endereco.Endereco;
import br.com.clinicanaifa.entity.Medico;
import br.com.clinicanaifa.exception.DuplicateResourceException;
import br.com.clinicanaifa.exception.ResourceNotFoundException;
import br.com.clinicanaifa.mapper.MedicoMapper;
import br.com.clinicanaifa.repository.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {


    private MedicoRepository medicoRepository;
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }


    public MedicoResponseDTO salvar(MedicoRequestDTO dto) {
        if (medicoRepository.existsByEmail(dto.email())) {
            throw new DuplicateResourceException("Email já cadastrado");
        }
        if (medicoRepository.existsByCrm(dto.crm())) {
            throw new DuplicateResourceException("CRM já cadastrado");
        }
        Medico medico = MedicoMapper.toEntity(dto);

        return MedicoMapper.toDTO(medicoRepository.save(medico));
    }



    public void deletar(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Médico não encontrado");
        }
        medicoRepository.deleteById(id);
    }



    public Page<MedicoResponseDTO> listarTodos(Pageable pageable) {
        return medicoRepository.findAll(pageable).map(MedicoMapper::toDTO);
    }



    public MedicoResponseDTO buscarPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
        return MedicoMapper.toDTO(medico);
    }



    public MedicoResponseDTO atualizar(Long id, MedicoRequestDTO dto) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
        medico.setNome(dto.nome());
        medico.setEmail(dto.email());
        medico.setCrm(dto.crm());
        medico.setTelefone(dto.telefone());
        medico.setEspecialidade(dto.especialidade());
        medico.setEndereco(
                new Endereco(
                        dto.endereco().bairro(),
                        dto.endereco().logradouro(),
                        dto.endereco().complemento(),
                        dto.endereco().cep(),
                        dto.endereco().numero(),
                        dto.endereco().cidade(),
                        dto.endereco().uf()
                )
        );
        Medico atualizado = medicoRepository.save(medico);
        return MedicoMapper.toDTO(atualizado);
    }
}
