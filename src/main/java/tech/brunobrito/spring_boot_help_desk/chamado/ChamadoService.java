package tech.brunobrito.spring_boot_help_desk.chamado;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.brunobrito.spring_boot_help_desk.chamado.dto.AbrirChamadoRequest;
import tech.brunobrito.spring_boot_help_desk.chamado.dto.ChamadoResponse;
import tech.brunobrito.spring_boot_help_desk.infra.exception.RecursoNaoEncontradoException;
import tech.brunobrito.spring_boot_help_desk.setor.Setor;
import tech.brunobrito.spring_boot_help_desk.setor.SetorRepository;
import tech.brunobrito.spring_boot_help_desk.usuario.Usuario;
import tech.brunobrito.spring_boot_help_desk.usuario.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final SetorRepository setorRepository;

    public ChamadoService(ChamadoRepository chamadoRepository,
            UsuarioRepository usuarioRepository,
            SetorRepository setorRepository) {
        this.chamadoRepository = chamadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.setorRepository = setorRepository;
    }

    @Transactional
    public ChamadoResponse abrir(AbrirChamadoRequest request) {
        Usuario solicitante = usuarioRepository.findById(request.solicitanteId())
                .filter(Usuario::isAtivo)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Usuário " + request.solicitanteId() + " não encontrado"));

        

        Setor setor = setorRepository.findById(request.setorId())
                .filter(Setor::isAtivo)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Setor " + request.setorId() + " não encontrado"));

        Chamado chamado = new Chamado(request.titulo(), request.descricao(),
                request.prioridade(), solicitante, setor);

        return ChamadoResponse.de(chamadoRepository.save(chamado));
    }

    @Transactional(readOnly = true)
    public Page<ChamadoResponse> listar(StatusChamado status, Pageable pageable) {
        Page<Chamado> pagina = (status == null)
                ? chamadoRepository.findAll(pageable)
                : chamadoRepository.findByStatus(status, pageable);
        return pagina.map(ChamadoResponse::de);
    }

    @Transactional(readOnly = true)
    public ChamadoResponse detalhar(Long id) {
        return chamadoRepository.findById(id)
                .map(ChamadoResponse::de)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Chamado " + id + " não encontrado"));
    }
}               