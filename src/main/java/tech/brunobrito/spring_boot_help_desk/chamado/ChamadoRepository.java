package tech.brunobrito.spring_boot_help_desk.chamado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    Page<Chamado> findByStatus(StatusChamado status, Pageable pageable);
}