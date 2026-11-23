package barbearia.repositorio;

import barbearia.excecao.ValidacaoException;
import barbearia.modelo.Servico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServicoRepositorioTest {

    private ServicoRepositorio repositorio;

    @BeforeEach
    void setUp() {
        repositorio = new ServicoRepositorio();
    }

    @Test
    void deveGerarProximoIdSequencialmente() {
        assertEquals(1, repositorio.proximoId());
        repositorio.salvar(new Servico(repositorio.proximoId(), "Corte", 35.0, 30));
        assertEquals(2, repositorio.proximoId());
    }

    @Test
    void deveSalvarServicoValido() {
        Servico servico = new Servico(repositorio.proximoId(), "Corte Degradê", 40.0, 35);
        Servico salvo = repositorio.salvar(servico);
        assertNotNull(salvo);
        assertEquals(1, salvo.getId());
        assertEquals("Corte Degradê", salvo.getNome());
    }

    @Test
    void deveLancarExcecaoAoSalvarServicoNuloOuComIdDuplicado() {
        assertThrows(ValidacaoException.class, () -> repositorio.salvar(null));

        Servico s1 = new Servico(1, "Corte", 35.0, 30);
        Servico s2 = new Servico(1, "Barba", 25.0, 20);
        repositorio.salvar(s1);
        assertThrows(ValidacaoException.class, () -> repositorio.salvar(s2));
    }
}
