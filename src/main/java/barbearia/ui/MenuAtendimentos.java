package barbearia.ui;

import barbearia.excecao.RecursoNaoEncontradoException;
import barbearia.excecao.ValidacaoException;
import barbearia.modelo.Atendimento;
import barbearia.modelo.Cliente;
import barbearia.modelo.Servico;
import barbearia.repositorio.AtendimentoRepositorio;
import barbearia.repositorio.ClienteRepositorio;
import barbearia.repositorio.ServicoRepositorio;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuAtendimentos {
    private final AtendimentoRepositorio repositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ServicoRepositorio servicoRepositorio;
    private final Scanner scanner;

    public MenuAtendimentos(
            AtendimentoRepositorio repositorio,
            ClienteRepositorio clienteRepositorio,
            ServicoRepositorio servicoRepositorio,
            Scanner scanner
    ) {
        this.repositorio = repositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.servicoRepositorio = servicoRepositorio;
        this.scanner = scanner;
    }

    public void exibir() {
        while (true) {
            System.out.println("\n=== MENU DE ATENDIMENTOS ===");
            System.out.println("1 - Registrar atendimento");
            System.out.println("2 - Buscar por ID");
            System.out.println("3 - Listar por cliente");
            System.out.println("4 - Listar por período");
            System.out.println("5 - Editar data e valor");
            System.out.println("6 - Excluir atendimento");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
                continue;
            }

            try {
                switch (opcao) {
                    case 1 -> registrar();
                    case 2 -> buscarPorId();
                    case 3 -> listarPorCliente();
                    case 4 -> listarPorPeriodo();
                    case 5 -> editar();
                    case 6 -> excluir();
                    case 0 -> {
                        System.out.println("Voltando ao menu principal...");
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (ValidacaoException | RecursoNaoEncontradoException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void registrar() {
        System.out.print("ID do atendimento: ");
        int id = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("ID do cliente: ");
        int clienteId = Integer.parseInt(scanner.nextLine().trim());
        Cliente cliente = clienteRepositorio.buscarPorId(clienteId);

        System.out.print("ID do serviço: ");
        int servicoId = Integer.parseInt(scanner.nextLine().trim());
        Servico servico = servicoRepositorio.buscarPorId(servicoId);

        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine().trim());

        Atendimento atendimento = new Atendimento(id, cliente, servico, data);
        repositorio.registrar(atendimento);
        System.out.println("Atendimento registrado com sucesso.");
    }

    private void buscarPorId() {
        System.out.print("ID do atendimento: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        Atendimento atendimento = repositorio.buscarPorId(id);
        imprimirAtendimento(atendimento);
    }

    private void listarPorCliente() {
        System.out.print("ID do cliente: ");
        int clienteId = Integer.parseInt(scanner.nextLine().trim());
        List<Atendimento> lista = repositorio.listarPorCliente(clienteId);
        if (lista.isEmpty()) {
            System.out.println("Nenhum atendimento encontrado para este cliente.");
            return;
        }
        lista.forEach(this::imprimirAtendimento);
    }

    private void listarPorPeriodo() {
        System.out.print("Data inicial (AAAA-MM-DD): ");
        LocalDate inicio = LocalDate.parse(scanner.nextLine().trim());
        System.out.print("Data final (AAAA-MM-DD): ");
        LocalDate fim = LocalDate.parse(scanner.nextLine().trim());

        List<Atendimento> lista = repositorio.listarPorPeriodo(inicio, fim);
        if (lista.isEmpty()) {
            System.out.println("Nenhum atendimento encontrado no período.");
            return;
        }
        lista.forEach(this::imprimirAtendimento);
    }

    private void editar() {
        System.out.print("ID do atendimento: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        Atendimento existente = repositorio.buscarPorId(id);

        System.out.print("Nova data (AAAA-MM-DD): ");
        LocalDate novaData = LocalDate.parse(scanner.nextLine().trim());

        System.out.print("Novo valor cobrado: ");
        double novoValor = Double.parseDouble(scanner.nextLine().trim());

        existente.setData(novaData);
        existente.setValorCobrado(novoValor);
        repositorio.atualizar(existente);
        System.out.println("Atendimento atualizado com sucesso.");
    }

    private void excluir() {
        System.out.print("ID do atendimento: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        repositorio.excluir(id);
        System.out.println("Atendimento excluído com sucesso.");
    }

    private void imprimirAtendimento(Atendimento a) {
        System.out.printf("ID: %d | Cliente: %s | Serviço: %s | Data: %s | Valor: R$ %.2f%n",
                a.getId(),
                a.getCliente().getNome(),
                a.getServico().getNome(),
                a.getData(),
                a.getValorCobrado());
    }
}