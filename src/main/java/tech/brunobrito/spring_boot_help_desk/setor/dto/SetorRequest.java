package tech.brunobrito.spring_boot_help_desk.setor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SetorRequest(
        @NotBlank @Size(max = 80) String nome) {
}
