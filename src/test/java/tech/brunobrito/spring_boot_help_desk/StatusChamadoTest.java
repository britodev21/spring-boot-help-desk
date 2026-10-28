package tech.brunobrito.spring_boot_help_desk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import tech.brunobrito.spring_boot_help_desk.chamado.StatusChamado;

class StatusChamadoTest {

    @ParameterizedTest
    @CsvSource({
            "ABERTO, EM_ATENDIMENTO",
            "ABERTO, CANCELADO",
            "EM_ATENDIMENTO, AGUARDANDO_SOLICITANTE",
            "EM_ATENDIMENTO, RESOLVIDO",
            "AGUARDANDO_SOLICITANTE, EM_ATENDIMENTO",
            "RESOLVIDO, FECHADO",
            "RESOLVIDO, EM_ATENDIMENTO"
    })

    void transicoesPermitidas(StatusChamado atual, StatusChamado novo) {
        assertTrue(atual.podeIrPara(novo));
    }

    @Test
    void existemExatamenteSeteTransicoesPermitidas() {
        long permitidas = Arrays.stream(StatusChamado.values())
                .flatMap(atual -> Arrays.stream(StatusChamado.values()).filter(atual::podeIrPara))
                .count();

        assertEquals(7, permitidas);
    }

    @ParameterizedTest
    @EnumSource(StatusChamado.class)
    void fechadoNaoVaiParaNenhumStatus(StatusChamado novo) {
        assertFalse(StatusChamado.FECHADO.podeIrPara(novo));
    }

    @ParameterizedTest
    @EnumSource(StatusChamado.class)
    void canceladoNaoVaiParaNenhumStatus(StatusChamado novo) {
        assertFalse(StatusChamado.CANCELADO.podeIrPara(novo));
    }

    @ParameterizedTest
    @EnumSource(value = StatusChamado.class, names = {"FECHADO", "CANCELADO"})
    void fechadoECanceladoSaoFinais(StatusChamado status) {
    assertTrue(status.isFinal());
    }

    @ParameterizedTest
    @EnumSource(value = StatusChamado.class, names = {"FECHADO", "CANCELADO"},
        mode = EnumSource.Mode.EXCLUDE)
    void demaisStatusNaoSaoFinais(StatusChamado status) {
        assertFalse(status.isFinal());
    }
}