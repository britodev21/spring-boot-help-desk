package tech.brunobrito.spring_boot_help_desk.chamado;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import tech.brunobrito.spring_boot_help_desk.setor.Setor;
import tech.brunobrito.spring_boot_help_desk.usuario.Usuario;

@Entity
@Table(name = "chamados")
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Enumerated(EnumType.STRING)
    private StatusChamado status;

    private Instant prazoSla;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setor_id")
    private Setor setor;

    private Instant criadoEm;

    private Instant atualizadoEm;

    private Instant resolvidoEm;

    protected Chamado() {
    }

    public Chamado(String titulo, String descricao, Prioridade prioridade,
            Usuario solicitante, Setor setor) {
        Instant agora = Instant.now();
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.solicitante = solicitante;
        this.setor = setor;
        this.status = StatusChamado.ABERTO;
        this.prazoSla = prioridade.calcularPrazo(agora);
        this.criadoEm = agora;
        this.atualizadoEm = agora;
    }

    public boolean isAtrasado() {
        return !status.isEncerrado() && Instant.now().isAfter(prazoSla);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public Instant getPrazoSla() {
        return prazoSla;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public Setor getSetor() {
        return setor;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public Instant getResolvidoEm() {
        return resolvidoEm;
    }
}