package barbearia.ui;

import barbearia.excecao.RecursoNaoEncontradoException;
import barbearia.excecao.ValidacaoException;
import barbearia.modelo.Servico;
import barbearia.repositorio.ServicoRepositorio;
import java.util.List;
import java.util.Scanner;

public class MenuServicos {

    private final ServicoRepositorio repositorio;
    private final Scanner scanner;

    public MenuServicos(ServicoRepositorio repositorio, Scanner scanner) {
        this.repositorio = repositorio;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== MENU DE SERVIÇOS ===");
            System.out.println("1. Cadastrar serviço");
            System.out.println("2. Listar serviços");
            System.out.println("3. Buscar serviço por nome");
            System.out.println("4. Editar serviço");
            System.out.println("5. Excluir serviço");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> buscarPorNome();
                case 4 -> editar();
                case 5 -> excluir();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrar() {
        try {
            System.out.print("Nome do serviço: ");
            String nome = scanner.nextLine();
            System.out.print("Preço (ex: 35.00): ");
            double preco = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            System.out.print("Duração em minutos: ");
            int duracao = Integer.parseInt(scanner.nextLine().trim());

            int id = repositorio.proximoId();
            Servico novo = new Servico(id, nome, preco, duracao);
            repositorio.salvar(novo);
            System.out.println("Serviço cadastrado com sucesso! " + novo);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Valor numérico inválido.");
        } catch (ValidacaoException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        }
    }

    private void listar() {
        List<Servico> servicos = repositorio.listar();
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Serviços ---");
        for (Servico s : servicos) {
            System.out.println(s);
        }
    }

    private void buscarPorNome() {
        System.out.print("Digite o nome ou trecho para buscar: ");
        String trecho = scanner.nextLine();
        List<Servico> encontrados = repositorio.buscarPorNome(trecho);
        if (encontrados.isEmpty()) {
            System.out.println("Nenhum serviço encontrado.");
            return;
        }
        for (Servico s : encontrados) {
            System.out.println(s);
        }
    }

    private void editar() {
        try {
            System.out.print("ID do serviço a editar: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Servico atual = repositorio.buscarPorId(id);

            System.out.print("Novo nome (" + atual.getNome() + "): ");
            String nome = scanner.nextLine();
            System.out.print("Novo preço (" + atual.getPreco() + "): ");
            double preco = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            System.out.print("Nova duração em min (" + atual.getDuracaoMinutos() + "): ");
            int duracao = Integer.parseInt(scanner.nextLine().trim());

            Servico atualizado = new Servico(id, nome, preco, duracao);
            repositorio.atualizar(atualizado);
            System.out.println("Serviço atualizado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Valor numérico inválido.");
        } catch (ValidacaoException | RecursoNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void excluir() {
        try {
            System.out.print("ID do serviço a excluir: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            repositorio.excluir(id);
            System.out.println("Serviço excluído com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID inválido.");
        } catch (RecursoNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
