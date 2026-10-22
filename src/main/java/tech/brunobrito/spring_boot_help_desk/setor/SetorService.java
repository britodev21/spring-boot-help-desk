package tech.brunobrito.spring_boot_help_desk.setor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import tech.brunobrito.spring_boot_help_desk.infra.exception.RecursoNaoEncontradoException;
import tech.brunobrito.spring_boot_help_desk.setor.dto.SetorRequest;
import tech.brunobrito.spring_boot_help_desk.setor.dto.SetorResponse;

@Service 
public class SetorService {

    private final SetorRepository repository;

    public SetorService(SetorRepository repository){
        this.repository = repository;
    }

    @Transactional
    public SetorResponse criar(SetorRequest request){
        Setor setor = new Setor(request.nome());
        return SetorResponse.de(repository.save(setor));
    }

    @Transactional(readOnly = true)
    public List<SetorResponse> listarAtivos() {
        return repository.findByAtivoTrue()
            .stream()
            .map(SetorResponse::de)
            .toList();
    }

    @Transactional
    public SetorResponse desativar(Long id) {
        Setor setor = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Setor " + id + " não encontrado"));
        setor.desativar();
        return SetorResponse.de(setor);
    }

}
