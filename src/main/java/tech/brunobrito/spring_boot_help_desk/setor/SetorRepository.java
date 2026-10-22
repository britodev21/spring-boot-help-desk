package tech.brunobrito.spring_boot_help_desk.setor;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetorRepository extends JpaRepository<Setor, Long> {
    List<Setor> findByAtivoTrue();
}
