package tech.brunobrito.spring_boot_help_desk.chamado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    @EntityGraph(attributePaths = {"solicitante", "tecnico", "setor"})
    Page<Chamado> findByStatus(StatusChamado status, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"solicitante", "tecnico", "setor"})
    Page<Chamado> findAll(Pageable pageable);
}