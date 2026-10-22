package tech.brunobrito.spring_boot_help_desk.Setor;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "setores")

public class Setor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    private String nome;
    private boolean ativo = true;

    protected Setor() {
    }

    public Setor(String nome) {
        this.nome = nome;
    }

    public void desativar() {
        this.ativo = false;
    }

    public long getId() {
        return Id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
