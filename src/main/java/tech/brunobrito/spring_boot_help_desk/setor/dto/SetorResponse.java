package tech.brunobrito.spring_boot_help_desk.setor.dto;

import tech.brunobrito.spring_boot_help_desk.setor.Setor;

public record SetorResponse(Long id, String nome, boolean ativo) {
    public static SetorResponse de(Setor Setor) {
        return new SetorResponse(Setor.getId(), Setor.getNome(), Setor.isAtivo());
    }

}
