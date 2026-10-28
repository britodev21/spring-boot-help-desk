package tech.brunobrito.spring_boot_help_desk.comentario;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    @EntityGraph(attributePaths = "autor")
    List<Comentario> findByChamadoIdOrderByCriadoEmAsc(Long chamadoId);
}