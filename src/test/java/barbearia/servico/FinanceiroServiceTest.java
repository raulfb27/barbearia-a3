package barbearia.servico;

import barbearia.excecao.ValidacaoException;
import barbearia.modelo.Atendimento;
import barbearia.modelo.Cliente;
import barbearia.modelo.Servico;
import barbearia.repositorio.AtendimentoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FinanceiroServiceTest {

    private List<Atendimento> dadosSimulados;
    private FinanceiroService financeiroService;

    @BeforeEach
    void setUp() {
        dadosSimulados = new ArrayList<>();

        AtendimentoRepositorio repositorioStub = new AtendimentoRepositorio() {
            @Override
            public List<Atendimento> listar() {
                return new ArrayList<>(dadosSimulados);
            }

            @Override
            public List<Atendimento> listarPorCliente(int idCliente) {
                return dadosSimulados.stream()
                        .filter(a -> a.getCliente() != null && a.getCliente().getId() == idCliente)
                        .toList();
            }

            @Override
            public List<Atendimento> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
                return dadosSimulados.stream()
                        .filter(a -> a.getData() != null
                                && !a.getData().isBefore(inicio)
                                && !a.getData().isAfter(fim))
                        .toList();
            }
        };

        Cliente c1 = new Cliente(1, "Carlos", "31999990001", "carlos@email.com");
        Cliente c2 = new Cliente(2, "Ana", "31999990002", "ana@email.com");

        Servico s1 = new Servico(1, "Corte", 40.0, 30);
        Servico s2 = new Servico(2, "Barba", 30.0, 20);

        dadosSimulados.add(new Atendimento(1, c1, s1, LocalDate.of(2026, 11, 10)));
        dadosSimulados.add(new Atendimento(2, c1, s2, LocalDate.of(2026, 11, 15)));
        dadosSimulados.add(new Atendimento(3, c2, s1, LocalDate.of(2026, 11, 20)));

        financeiroService = new FinanceiroService(repositorioStub);
    }

    @Test
    void deveCalcularTotalPorCliente() {
        assertEquals(70.0, financeiroService.totalPorCliente(1), 0.001);
        assertEquals(40.0, financeiroService.totalPorCliente(2), 0.001);
        assertEquals(0.0, financeiroService.totalPorCliente(99), 0.001);
    }

    @Test
    void deveCalcularFaturamentoPorPeriodo() {
        double total = financeiroService.faturamentoPorPeriodo(
                LocalDate.of(2026, 11, 10),
                LocalDate.of(2026, 11, 16)
        );
        assertEquals(70.0, total, 0.001);
    }

    @Test
    void deveLancarExcecaoQuandoPeriodoInvalido() {
        LocalDate inicio = LocalDate.of(2026, 11, 20);
        LocalDate fim = LocalDate.of(2026, 11, 10);

        assertThrows(ValidacaoException.class, () ->
                financeiroService.faturamentoPorPeriodo(inicio, fim)
        );
        assertThrows(ValidacaoException.class, () ->
                financeiroService.faturamentoPorPeriodo(null, fim)
        );
    }

    @Test
    void deveCalcularFaturamentoPorServico() {
        assertEquals(80.0, financeiroService.faturamentoPorServico(1), 0.001);
        assertEquals(30.0, financeiroService.faturamentoPorServico(2), 0.001);
        assertEquals(0.0, financeiroService.faturamentoPorServico(99), 0.001);
    }

    @Test
    void deveCalcularTicketMedio() {
        // (40 + 30 + 40) / 3 = 36.666...
        assertEquals(36.666, financeiroService.ticketMedio(), 0.01);
    }

    @Test
    void deveRetornarZeroNoTicketMedioQuandoNaoHouverAtendimentos() {
        dadosSimulados.clear();
        assertEquals(0.0, financeiroService.ticketMedio(), 0.001);
    }
}