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
}
