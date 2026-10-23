package tech.brunobrito.spring_boot_help_desk.chamado.dto;

import java.time.Instant;

import tech.brunobrito.spring_boot_help_desk.chamado.Chamado;
import tech.brunobrito.spring_boot_help_desk.chamado.Prioridade;
import tech.brunobrito.spring_boot_help_desk.chamado.StatusChamado;
import tech.brunobrito.spring_boot_help_desk.usuario.Usuario;

public record ChamadoResponse(
        Long id,
        String titulo,
        String descricao,
        Prioridade prioridade,
        StatusChamado status,
        Instant prazoSla,
        boolean atrasado,
        ResumoUsuario solicitante,
        ResumoUsuario tecnico,
        ResumoSetor setor,
        Instant criadoEm
) {

    public record ResumoUsuario(Long id, String nome) {
    }

    public record ResumoSetor(Long id, String nome) {
    }

    public static ChamadoResponse de(Chamado chamado) {
        Usuario tecnico = chamado.getTecnico();
        return new ChamadoResponse(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getPrioridade(),
                chamado.getStatus(),
                chamado.getPrazoSla(),
                chamado.isAtrasado(),
                new ResumoUsuario(chamado.getSolicitante().getId(), chamado.getSolicitante().getNome()),
                tecnico == null ? null : new ResumoUsuario(tecnico.getId(), tecnico.getNome()),
                new ResumoSetor(chamado.getSetor().getId(), chamado.getSetor().getNome()),
                chamado.getCriadoEm());
    }
}