package tech.brunobrito.spring_boot_help_desk.usuario.dto;

import tech.brunobrito.spring_boot_help_desk.usuario.Perfil;
import tech.brunobrito.spring_boot_help_desk.usuario.Usuario;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        Perfil perfil,
        Long setorId,
        String setorNome,
        boolean ativo
) {

    public static UsuarioResponse de(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil(),
                usuario.getSetor().getId(),
                usuario.getSetor().getNome(),
                usuario.isAtivo());
    }
}