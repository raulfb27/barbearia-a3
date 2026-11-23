package barbearia.repositorio;

import static org.junit.jupiter.api.Assertions.*;

import barbearia.excecao.RecursoNaoEncontradoException;
import barbearia.excecao.ValidacaoException;
import barbearia.modelo.Cliente;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteRepositorioTest {

    private ClienteRepositorio repositorio;
    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setUp() {
        repositorio = new ClienteRepositorio();
        cliente1 = new Cliente(1, "Marcos Silva", "11911112222", "marcos@barbearia.com");
        cliente2 = new Cliente(2, "Mariana Souza", "11933334444", "mariana@barbearia.com");
    }

    @Test
    void deveSalvarEBuscarClientePorId() {
        repositorio.salvar(cliente1);
        Cliente encontrado = repositorio.buscarPorId(1);

        assertEquals(cliente1, encontrado);
        assertEquals("Marcos Silva", encontrado.getNome());
    }

    @Test
    void deveLancarExcecaoAoSalvarDuplicadoOuNulo() {
        repositorio.salvar(cliente1);

        assertThrows(ValidacaoException.class, () -> repositorio.salvar(cliente1));
        assertThrows(ValidacaoException.class, () -> repositorio.salvar(null));
    }

    @Test
    void deveLancarExcecaoAoBuscarIdInexistenteOuInvalido() {
        assertThrows(RecursoNaoEncontradoException.class, () -> repositorio.buscarPorId(999));
        assertThrows(ValidacaoException.class, () -> repositorio.buscarPorId(0));
    }

    @Test
    void deveListarTodosOsClientes() {
        assertTrue(repositorio.listar().isEmpty());

        repositorio.salvar(cliente1);
        repositorio.salvar(cliente2);

        List<Cliente> todos = repositorio.listar();
        assertEquals(2, todos.size());
    }

    @Test
    void deveAtualizarClienteExistenteELancarExcecaoSeNaoExistir() {
        repositorio.salvar(cliente1);
        Cliente atualizado = new Cliente(1, "Marcos Silva Atualizado", "11955556666", "marcos.novo@barbearia.com");

        repositorio.atualizar(atualizado);
        assertEquals("Marcos Silva Atualizado", repositorio.buscarPorId(1).getNome());

        Cliente inexistente = new Cliente(99, "Fantasma", "11955556666", "f@barbearia.com");
        assertThrows(RecursoNaoEncontradoException.class, () -> repositorio.atualizar(inexistente));
    }

    @Test
    void deveExcluirClienteExistenteELancarExcecaoSeNaoExistir() {
        repositorio.salvar(cliente1);
        repositorio.excluir(1);

        assertThrows(RecursoNaoEncontradoException.class, () -> repositorio.buscarPorId(1));
        assertThrows(RecursoNaoEncontradoException.class, () -> repositorio.excluir(1));
    }

    @Test
    void deveBuscarClientesPorNomeParcialOuRetornarListaVazia() {
        repositorio.salvar(cliente1);
        repositorio.salvar(cliente2);

        List<Cliente> encontrados = repositorio.buscarPorNome("mar");
        assertEquals(2, encontrados.size());

        List<Cliente> vazio = repositorio.buscarPorNome("inexistente");
        assertTrue(vazio.isEmpty());
        assertThrows(ValidacaoException.class, () -> repositorio.buscarPorNome(" "));
    }
}