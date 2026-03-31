package br.com.clinicanaifa.repository;

import br.com.clinicanaifa.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    boolean existsByEmail(String email);
    boolean existsByCrm(String crm);
}
