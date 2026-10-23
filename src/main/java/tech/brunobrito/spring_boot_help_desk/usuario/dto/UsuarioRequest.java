package tech.brunobrito.spring_boot_help_desk.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tech.brunobrito.spring_boot_help_desk.usuario.Perfil;

public record UsuarioRequest(
        @NotBlank
        @Size(max = 120)
        String nome,

        @NotBlank
        @Email
        @Size(max = 160)
        String email,

        @NotNull
        Perfil perfil,

        @NotNull
        Long setorId
) {
}