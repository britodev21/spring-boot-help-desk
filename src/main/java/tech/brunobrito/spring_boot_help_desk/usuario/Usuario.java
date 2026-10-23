package tech.brunobrito.spring_boot_help_desk.usuario;

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

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String nome;
    private String email;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setor_id")

    private Setor setor;
    private boolean ativo = true;
    private Instant criadoEm;

    protected Usuario() {
    }

    public Usuario(String nome, String email, Perfil perfil, Setor setor) {
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
        this.setor = setor;
        this.criadoEm = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public Setor getSetor() {
        return setor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

}
