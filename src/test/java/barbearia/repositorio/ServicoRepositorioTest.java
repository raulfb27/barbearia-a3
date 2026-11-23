package barbearia.repositorio;

import barbearia.excecao.RecursoNaoEncontradoException;
import barbearia.excecao.ValidacaoException;
import barbearia.modelo.Servico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
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

    @Test
    void deveBuscarServicoPorIdExistente() {
        repositorio.salvar(new Servico(1, "Corte", 35.0, 30));
        repositorio.salvar(new Servico(2, "Barba", 25.0, 20));

        Servico encontrado = repositorio.buscarPorId(2);
        assertNotNull(encontrado);
        assertEquals("Barba", encontrado.getNome());
    }

    @Test
    void deveLancarExcecaoAoBuscarServicoPorIdInexistente() {
        repositorio.salvar(new Servico(1, "Corte", 35.0, 30));
        assertThrows(RecursoNaoEncontradoException.class, () -> repositorio.buscarPorId(99));
        assertThrows(RecursoNaoEncontradoException.class, () -> repositorio.buscarPorId(0));
    }

    @Test
    void deveListarTodosOsServicosCadastrados() {
        assertTrue(repositorio.listar().isEmpty());

        repositorio.salvar(new Servico(1, "Corte", 35.0, 30));
        repositorio.salvar(new Servico(2, "Barba", 25.0, 20));

        List<Servico> lista = repositorio.listar();
        assertEquals(2, lista.size());
    }

    @Test
    void deveBuscarServicosPorTrechoDoNomeIgnorandoMaiusculas() {
        repositorio.salvar(new Servico(1, "Corte Social", 30.0, 30));
        repositorio.salvar(new Servico(2, "Corte Degradê", 40.0, 40));
        repositorio.salvar(new Servico(3, "Barba", 25.0, 20));

        List<Servico> encontrados = repositorio.buscarPorNome("corte");
        assertEquals(2, encontrados.size());
    }

    @Test
    void deveRetornarListaVaziaQuandoNenhumServicoCorresponderAoNome() {
        repositorio.salvar(new Servico(1, "Corte", 30.0, 30));
        assertTrue(repositorio.buscarPorNome("Pigmentação").isEmpty());
        assertTrue(repositorio.buscarPorNome(null).isEmpty());
        assertTrue(repositorio.buscarPorNome("   ").isEmpty());
    }

    @Test
    void deveAtualizarServicoExistente() {
        repositorio.salvar(new Servico(1, "Corte", 30.0, 30));
        Servico atualizado = new Servico(1, "Corte Navalhado", 45.0, 40);

        repositorio.atualizar(atualizado);

        Servico buscado = repositorio.buscarPorId(1);
        assertEquals("Corte Navalhado", buscado.getNome());
        assertEquals(45.0, buscado.getPreco());
        assertEquals(40, buscado.getDuracaoMinutos());
    }

    @Test
    void deveLancarExcecaoAoAtualizarServicoNuloOuInexistente() {
        assertThrows(ValidacaoException.class, () -> repositorio.atualizar(null));
        assertThrows(RecursoNaoEncontradoException.class,
                () -> repositorio.atualizar(new Servico(99, "Inexistente", 20.0, 15)));
    }

    @Test
    void deveExcluirServicoExistente() {
        repositorio.salvar(new Servico(1, "Corte", 30.0, 30));
        repositorio.excluir(1);

        assertTrue(repositorio.listar().isEmpty());
        assertThrows(RecursoNaoEncontradoException.class, () -> repositorio.buscarPorId(1));
    }

    @Test
    void deveLancarExcecaoAoExcluirServicoInexistente() {
        assertThrows(RecursoNaoEncontradoException.class, () -> repositorio.excluir(99));
    }
}
