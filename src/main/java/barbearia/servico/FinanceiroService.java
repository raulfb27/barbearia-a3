package barbearia.servico;

import barbearia.repositorio.AtendimentoRepositorio;
import java.time.LocalDate;

public class FinanceiroService {
    public FinanceiroService(AtendimentoRepositorio repositorio) {}
    public double totalPorCliente(int idCliente) { return 0.0; }
    public double faturamentoPorPeriodo(LocalDate inicio, LocalDate fim) { return 0.0; }
    public double faturamentoPorServico(int idServico) { return 0.0; }
    public double ticketMedio() { return 0.0; }
}
