package tech.brunobrito.spring_boot_help_desk.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tech.brunobrito.spring_boot_help_desk.usuario.dto.UsuarioRequest;
import tech.brunobrito.spring_boot_help_desk.usuario.dto.UsuarioResponse;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@RequestBody @Valid UsuarioRequest request) {
        UsuarioResponse criado = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }
}