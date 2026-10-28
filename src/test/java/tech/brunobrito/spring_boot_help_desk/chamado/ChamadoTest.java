package tech.brunobrito.spring_boot_help_desk.chamado;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import tech.brunobrito.spring_boot_help_desk.infra.exception.RegraNegocioException;
import tech.brunobrito.spring_boot_help_desk.usuario.Perfil;
import tech.brunobrito.spring_boot_help_desk.usuario.Usuario;

class ChamadoTest {

    private Usuario tecnico;
    private Chamado chamado;

    @BeforeEach
    void preparar() {
        Usuario solicitante = new Usuario("Ana", "ana@empresa.com", Perfil.SOLICITANTE, null);
        tecnico = new Usuario("Carlos", "carlos@empresa.com", Perfil.TECNICO, null);
        chamado = new Chamado("Impressora travada", "Não imprime desde ontem",
                Prioridade.MEDIA, solicitante, null);
    }

    @Test
    void assumirChamadoAbertoDefineTecnicoEStatus() {
        chamado.assumir(tecnico);

        assertEquals(StatusChamado.EM_ATENDIMENTO, chamado.getStatus());
        assertSame(tecnico, chamado.getTecnico());
    }

    @Test
    void naoAssumeChamadoJaAssumido() {
        chamado.assumir(tecnico);

        assertThrows(RegraNegocioException.class, () -> chamado.assumir(tecnico));
    }

    @Test
    void naoIniciaAtendimentoSemAssumir() {
        assertThrows(RegraNegocioException.class,
                () -> chamado.mudarStatus(StatusChamado.EM_ATENDIMENTO));
    }

    @Test
    void transicaoProibidaLancaExcecaoENaoMudaNada() {
        RegraNegocioException erro = assertThrows(RegraNegocioException.class,
                () -> chamado.mudarStatus(StatusChamado.RESOLVIDO));

        assertEquals("Não é possível ir de ABERTO para RESOLVIDO.", erro.getMessage());
        assertEquals(StatusChamado.ABERTO, chamado.getStatus());
    }

    @Test
    void resolverPreencheResolvidoEmEReabrirLimpa() {
        chamado.assumir(tecnico);
        chamado.mudarStatus(StatusChamado.RESOLVIDO);
        assertNotNull(chamado.getResolvidoEm());

        chamado.mudarStatus(StatusChamado.EM_ATENDIMENTO);
        assertNull(chamado.getResolvidoEm());
    }

    @ParameterizedTest
    @EnumSource(StatusChamado.class)
    void chamadoFechadoNaoAceitaNenhumStatus(StatusChamado novo) {
        chamado.assumir(tecnico);
        chamado.mudarStatus(StatusChamado.RESOLVIDO);
        chamado.mudarStatus(StatusChamado.FECHADO);

        RegraNegocioException erro = assertThrows(RegraNegocioException.class,
                () -> chamado.mudarStatus(novo));
        assertEquals("Chamado FECHADO não aceita mais mudanças.", erro.getMessage());
    }

    @Test
    void chamadoCanceladoNaoAceitaMudancas() {
        chamado.mudarStatus(StatusChamado.CANCELADO);

        assertThrows(RegraNegocioException.class, chamado::garantirQueAceitaMudancas);
    }

    @Test
    void chamadoResolvidoAindaAceitaMudancas() {
        chamado.assumir(tecnico);
        chamado.mudarStatus(StatusChamado.RESOLVIDO);

        assertDoesNotThrow(chamado::garantirQueAceitaMudancas);
    }
}