package barbearia.modelo;

import barbearia.excecao.ValidacaoException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServicoTest {

    @Test
    void deveCriarServicoComDadosValidos() {
        Servico servico = new Servico(1, "Corte Degradê", 35.0, 30);
        assertEquals(1, servico.getId());
        assertEquals("Corte Degradê", servico.getNome());
        assertEquals(35.0, servico.getPreco());
        assertEquals(30, servico.getDuracaoMinutos());
    }

    @Test
    void deveCriarServicoComValoresLimiteMinimos() {
        Servico servico = new Servico(1, "A", 0.01, 1);
        assertEquals("A", servico.getNome());
        assertEquals(0.01, servico.getPreco());
        assertEquals(1, servico.getDuracaoMinutos());
    }

    @Test
    void deveLancarExcecaoAoCriarServicoComIdInvalido() {
        assertThrows(ValidacaoException.class, () -> new Servico(0, "Corte", 35.0, 30));
    }

    @Test
    void deveAlterarNomeValido() {
        Servico servico = new Servico(1, "Corte", 35.0, 30);
        servico.setNome("Barba Completa");
        assertEquals("Barba Completa", servico.getNome());
    }

    @Test
    void deveRemoverEspacosExtrasAoAlterarNome() {
        Servico servico = new Servico(1, "Corte", 35.0, 30);
        servico.setNome("  Sobrancelha  ");
        assertEquals("Sobrancelha", servico.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForNuloOuVazio() {
        Servico servico = new Servico(1, "Corte", 35.0, 30);
        assertThrows(ValidacaoException.class, () -> servico.setNome(null));
        assertThrows(ValidacaoException.class, () -> servico.setNome(""));
        assertThrows(ValidacaoException.class, () -> servico.setNome("   "));
    }

    @Test
    void deveAlterarPrecoValido() {
        Servico servico = new Servico(1, "Corte", 35.0, 30);
        servico.setPreco(45.50);
        assertEquals(45.50, servico.getPreco());
    }

    @Test
    void deveAceitarPrecoLimiteMinimo() {
        Servico servico = new Servico(1, "Corte", 35.0, 30);
        servico.setPreco(0.01);
        assertEquals(0.01, servico.getPreco());
    }

    @Test
    void deveLancarExcecaoQuandoPrecoForZeroOuNegativo() {
        Servico servico = new Servico(1, "Corte", 35.0, 30);
        assertThrows(ValidacaoException.class, () -> servico.setPreco(0));
        assertThrows(ValidacaoException.class, () -> servico.setPreco(-10.0));
    }

    @Test
    void deveAlterarDuracaoValida() {
        Servico servico = new Servico(1, "Corte", 35.0, 30);
        servico.setDuracaoMinutos(45);
        assertEquals(45, servico.getDuracaoMinutos());
    }

    @Test
    void deveAceitarDuracaoLimiteMinima() {
        Servico servico = new Servico(1, "Corte", 35.0, 30);
        servico.setDuracaoMinutos(1);
        assertEquals(1, servico.getDuracaoMinutos());
    }

    @Test
    void deveLancarExcecaoQuandoDuracaoForZeroOuNegativa() {
        Servico servico = new Servico(1, "Corte", 35.0, 30);
        assertThrows(ValidacaoException.class, () -> servico.setDuracaoMinutos(0));
        assertThrows(ValidacaoException.class, () -> servico.setDuracaoMinutos(-15));
    }
}
