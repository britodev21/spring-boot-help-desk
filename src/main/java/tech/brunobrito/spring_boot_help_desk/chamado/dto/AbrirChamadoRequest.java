package tech.brunobrito.spring_boot_help_desk.chamado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tech.brunobrito.spring_boot_help_desk.chamado.Prioridade;

public record AbrirChamadoRequest(
        @NotBlank
        @Size(max = 150)
        String titulo,

        @NotBlank
        @Size(max = 2000)
        String descricao,

        @NotNull
        Prioridade prioridade,

        @NotNull
        Long setorId,

        @NotNull
        Long solicitanteId
) {
}