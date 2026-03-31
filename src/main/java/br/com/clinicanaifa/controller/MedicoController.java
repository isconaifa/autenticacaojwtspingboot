package br.com.clinicanaifa.controller;
import br.com.clinicanaifa.dto.request.MedicoRequestDTO;
import br.com.clinicanaifa.dto.response.MedicoResponseDTO;
import br.com.clinicanaifa.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("medicos")
@CrossOrigin("*")
public class MedicoController {

    private final MedicoService medicoService;
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }



    @PostMapping
    public ResponseEntity<MedicoResponseDTO> salvar(@RequestBody @Valid MedicoRequestDTO dto) {

        MedicoResponseDTO response = medicoService.salvar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> buscarPorId(@PathVariable Long id) {
        MedicoResponseDTO response = medicoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoResponseDTO>> listarTodos(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        Page<MedicoResponseDTO> lista = medicoService.listarTodos(pageable);
        return ResponseEntity.ok(lista);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid MedicoRequestDTO dto) {
        MedicoResponseDTO response = medicoService.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
