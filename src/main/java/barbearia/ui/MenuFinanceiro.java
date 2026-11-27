package barbearia.ui;

import barbearia.excecao.ValidacaoException;
import barbearia.servico.FinanceiroService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuFinanceiro {

    private final FinanceiroService financeiroService;
    private final Scanner scanner;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MenuFinanceiro(FinanceiroService financeiroService, Scanner scanner) {
        this.financeiroService = financeiroService;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== RELATÓRIOS FINANCEIROS ===");
            System.out.println("1. Total gasto por cliente");
            System.out.println("2. Faturamento por período");
            System.out.println("3. Faturamento por serviço");
            System.out.println("4. Ticket médio geral");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1 -> consultarTotalPorCliente();
                case 2 -> consultarFaturamentoPorPeriodo();
                case 3 -> consultarFaturamentoPorServico();
                case 4 -> consultarTicketMedio();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void consultarTotalPorCliente() {
        try {
            System.out.print("ID do cliente: ");
            int idCliente = Integer.parseInt(scanner.nextLine().trim());
            double total = financeiroService.totalPorCliente(idCliente);
            System.out.printf("Total gasto pelo cliente #%d: R$ %.2f%n", idCliente, total);
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID inválido.");
        }
    }

    private void consultarFaturamentoPorPeriodo() {
        try {
            System.out.print("Data inicial (dd/MM/yyyy): ");
            LocalDate inicio = LocalDate.parse(scanner.nextLine().trim(), formatter);
            System.out.print("Data final (dd/MM/yyyy): ");
            LocalDate fim = LocalDate.parse(scanner.nextLine().trim(), formatter);

            double total = financeiroService.faturamentoPorPeriodo(inicio, fim);
            System.out.printf("Faturamento de %s a %s: R$ %.2f%n",
                    inicio.format(formatter), fim.format(formatter), total);
        } catch (DateTimeParseException e) {
            System.out.println("Erro: Data inválida. Use o formato dd/MM/yyyy (ex: 10/11/2026).");
        } catch (ValidacaoException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        }
    }

    private void consultarFaturamentoPorServico() {
        try {
            System.out.print("ID do serviço: ");
            int idServico = Integer.parseInt(scanner.nextLine().trim());
            double total = financeiroService.faturamentoPorServico(idServico);
            System.out.printf("Faturamento do serviço #%d: R$ %.2f%n", idServico, total);
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID inválido.");
        }
    }

    private void consultarTicketMedio() {
        double media = financeiroService.ticketMedio();
        System.out.printf("Ticket médio dos atendimentos: R$ %.2f%n", media);
    }
}