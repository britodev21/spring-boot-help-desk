package tech.brunobrito.spring_boot_help_desk.comentario;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import tech.brunobrito.spring_boot_help_desk.chamado.Chamado;
import tech.brunobrito.spring_boot_help_desk.usuario.Usuario;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chamado_id", nullable = false)
    private Chamado chamado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @Column(nullable = false, length = 2000)
    private String texto;

    @Column(name = "criado_em", nullable = false)
    private Instant criadoEm;

    protected Comentario() {
        // exigido pelo JPA
    }

    public Comentario(Chamado chamado, Usuario autor, String texto) {
        this.chamado = chamado;
        this.autor = autor;
        this.texto = texto;
        this.criadoEm = Instant.now();
    }

    public Long getId() { return id; }
    public Chamado getChamado() { return chamado; }
    public Usuario getAutor() { return autor; }
    public String getTexto() { return texto; }
    public Instant getCriadoEm() { return criadoEm; }
}