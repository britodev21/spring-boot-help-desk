package tech.brunobrito.spring_boot_help_desk.Setor.dto;

import tech.brunobrito.spring_boot_help_desk.Setor.Setor;

public record SetorResponse(Long id, String nome, boolean ativo) {
    public static SetorResponse de(Setor Setor) {
        return new SetorResponse(Setor.getId(), Setor.getNome(), Setor.isAtivo());
    }

}
