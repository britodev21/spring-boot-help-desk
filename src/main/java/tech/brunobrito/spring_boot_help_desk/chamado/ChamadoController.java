package tech.brunobrito.spring_boot_help_desk.chamado;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tech.brunobrito.spring_boot_help_desk.chamado.dto.AbrirChamadoRequest;
import tech.brunobrito.spring_boot_help_desk.chamado.dto.ChamadoResponse;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private final ChamadoService service;

    public ChamadoController(ChamadoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ChamadoResponse> abrir(@RequestBody @Valid AbrirChamadoRequest request) {
        ChamadoResponse criado = service.abrir(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }
}