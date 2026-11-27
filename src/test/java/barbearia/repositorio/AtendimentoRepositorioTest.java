package barbearia.repositorio;

import barbearia.excecao.RecursoNaoEncontradoException;
import barbearia.excecao.ValidacaoException;
import barbearia.modelo.Atendimento;
import barbearia.modelo.Cliente;
import barbearia.modelo.Servico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AtendimentoRepositorioTest {

    private AtendimentoRepositorio repositorio;
    private Cliente cliente1;
    private Cliente cliente2;
    private Servico corte;

    @BeforeEach
    void setUp() {
        repositorio = new AtendimentoRepositorio();
        cliente1 = new Cliente(1, "Carlos", "11999999999", "carlos@email.com");
        cliente2 = new Cliente(2, "Bruno", "11888888888", "bruno@email.com");
        corte = new Servico(10, "Corte", 40.0, 30);
    }

    @Test
    void deveRegistrarBuscarListarAtualizarEExcluir() {
        Atendimento a1 = new Atendimento(1, cliente1, corte, LocalDate.of(2026, 11, 10));
        repositorio.registrar(a1);

        assertEquals(1, repositorio.buscarPorId(1).getId());

        List<Atendimento> porCliente = repositorio.listarPorCliente(1);
        assertEquals(1, porCliente.size());

        List<Atendimento> porPeriodo = repositorio.listarPorPeriodo(
                LocalDate.of(2026, 11, 1),
                LocalDate.of(2026, 11, 30)
        );
        assertEquals(1, porPeriodo.size());

        a1.setValorCobrado(55.0);
        repositorio.atualizar(a1);
        assertEquals(55.0, repositorio.buscarPorId(1).getValorCobrado());

        repositorio.excluir(1);
        assertThrows(RecursoNaoEncontradoException.class, () -> repositorio.buscarPorId(1));
    }

    @Test
    void deveBloquearIdDuplicado() {
        Atendimento a1 = new Atendimento(1, cliente1, corte, LocalDate.of(2026, 11, 10));
        Atendimento a2 = new Atendimento(1, cliente2, corte, LocalDate.of(2026, 11, 12));
        repositorio.registrar(a1);

        assertThrows(ValidacaoException.class, () -> repositorio.registrar(a2));
    }
}