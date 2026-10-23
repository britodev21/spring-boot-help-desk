package tech.brunobrito.spring_boot_help_desk.usuario;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.brunobrito.spring_boot_help_desk.infra.exception.ConflitoException;
import tech.brunobrito.spring_boot_help_desk.infra.exception.RecursoNaoEncontradoException;
import tech.brunobrito.spring_boot_help_desk.setor.Setor;
import tech.brunobrito.spring_boot_help_desk.setor.SetorRepository;
import tech.brunobrito.spring_boot_help_desk.usuario.dto.UsuarioRequest;
import tech.brunobrito.spring_boot_help_desk.usuario.dto.UsuarioResponse;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final SetorRepository setorRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, SetorRepository setorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.setorRepository = setorRepository;
    }

    @Transactional
    public UsuarioResponse criar(UsuarioRequest request) {
        String email = request.email().trim().toLowerCase();

        if (usuarioRepository.existsByEmail(email)) {
            throw new ConflitoException("Email " + email + " já cadastrado");
        }

        Setor setor = setorRepository.findById(request.setorId())
                .filter(Setor::isAtivo)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Setor " + request.setorId() + " não encontrado"));

        Usuario usuario = new Usuario(request.nome(), email, request.perfil(), setor);
        return UsuarioResponse.de(usuarioRepository.save(usuario));
    }
}