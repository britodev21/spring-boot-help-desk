package tech.brunobrito.spring_boot_help_desk.setor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tech.brunobrito.spring_boot_help_desk.setor.dto.SetorRequest;
import tech.brunobrito.spring_boot_help_desk.setor.dto.SetorResponse;

@RestController
@RequestMapping("/setores")
public class SetorController {

    private final SetorService service;

    public SetorController(SetorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SetorResponse> criar(@RequestBody @Valid SetorRequest request) {
        SetorResponse criado = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public List<SetorResponse> listar() {
        return service.listarAtivos();
    }

    @PatchMapping("/{id}/desativar")
    public SetorResponse desativar(@PathVariable Long id) {
        return service.desativar(id);
    }

}
