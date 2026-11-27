package barbearia.servico;

import barbearia.excecao.ValidacaoException;
import barbearia.modelo.Atendimento;
import barbearia.repositorio.AtendimentoRepositorio;
import java.time.LocalDate;
import java.util.List;

public class FinanceiroService {

    private static final LocalDate DATA_MINIMA = LocalDate.of(1900, 1, 1);
    private static final LocalDate DATA_MAXIMA = LocalDate.of(2100, 12, 31);

    private final AtendimentoRepositorio repositorio;

    public FinanceiroService(AtendimentoRepositorio repositorio) {
        if (repositorio == null) {
            throw new ValidacaoException("O repositório de atendimentos não pode ser nulo.");
        }
        this.repositorio = repositorio;
    }

    public double totalPorCliente(int idCliente) {
        return repositorio.listarPorCliente(idCliente).stream()
                .mapToDouble(Atendimento::getValorCobrado)
                .sum();
    }

    public double faturamentoPorPeriodo(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null) {
            throw new ValidacaoException("As datas de início e fim são obrigatórias.");
        }
        if (inicio.isAfter(fim)) {
            throw new ValidacaoException("A data de início não pode ser posterior à data de fim.");
        }
        return repositorio.listarPorPeriodo(inicio, fim).stream()
                .mapToDouble(Atendimento::getValorCobrado)
                .sum();
    }

    public double faturamentoPorServico(int idServico) {
        return repositorio.listarPorPeriodo(DATA_MINIMA, DATA_MAXIMA).stream()
                .filter(a -> a.getServico() != null && a.getServico().getId() == idServico)
                .mapToDouble(Atendimento::getValorCobrado)
                .sum();
    }

    public double ticketMedio() {
        List<Atendimento> todos = repositorio.listarPorPeriodo(DATA_MINIMA, DATA_MAXIMA);
        if (todos.isEmpty()) {
            return 0.0;
        }
        double soma = todos.stream()
                .mapToDouble(Atendimento::getValorCobrado)
                .sum();
        return soma / todos.size();
    }
}