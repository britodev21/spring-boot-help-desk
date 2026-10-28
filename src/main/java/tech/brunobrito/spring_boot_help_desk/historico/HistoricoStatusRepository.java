package tech.brunobrito.spring_boot_help_desk.historico;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoStatusRepository extends JpaRepository<HistoricoStatus, Long> {

    @EntityGraph(attributePaths = "alteradoPor")
    List<HistoricoStatus> findByChamadoIdOrderByAlteradoEmAsc(Long chamadoId);
}