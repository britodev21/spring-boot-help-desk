package tech.brunobrito.spring_boot_help_desk.historico;

import java.time.Instant;
import java.util.Objects;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
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
import tech.brunobrito.spring_boot_help_desk.chamado.Chamado;
import tech.brunobrito.spring_boot_help_desk.chamado.StatusChamado;
import tech.brunobrito.spring_boot_help_desk.usuario.Usuario;

@Entity
@Immutable
@Table(name = "historico_status")
public class HistoricoStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chamado_id", nullable = false)
    private Chamado chamado;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_anterior", length = 30)
    private StatusChamado statusAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_novo", nullable = false, length = 30)
    private StatusChamado statusNovo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alterado_por_id", nullable = false)
    private Usuario alteradoPor;

    @Column(name = "alterado_em", nullable = false)
    private Instant alteradoEm;

    protected HistoricoStatus() {
        // exigido pelo JPA
    }

    private HistoricoStatus(Chamado chamado, StatusChamado statusAnterior,
                            StatusChamado statusNovo, Usuario alteradoPor) {
        this.chamado = chamado;
        this.statusAnterior = statusAnterior;
        this.statusNovo = statusNovo;
        this.alteradoPor = alteradoPor;
        this.alteradoEm = Instant.now();
    }

    public static HistoricoStatus abertura(Chamado chamado, Usuario solicitante) {
        return new HistoricoStatus(chamado, null, StatusChamado.ABERTO, solicitante);
    }

    public static HistoricoStatus transicao(Chamado chamado, StatusChamado de,
                                            StatusChamado para, Usuario alteradoPor) {
        return new HistoricoStatus(chamado,
                Objects.requireNonNull(de, "status anterior é obrigatório numa transição"),
                Objects.requireNonNull(para, "status novo é obrigatório"),
                alteradoPor);
    }

    public Long getId() { return id; }
    public Chamado getChamado() { return chamado; }
    public StatusChamado getStatusAnterior() { return statusAnterior; }
    public StatusChamado getStatusNovo() { return statusNovo; }
    public Usuario getAlteradoPor() { return alteradoPor; }
    public Instant getAlteradoEm() { return alteradoEm; }
}