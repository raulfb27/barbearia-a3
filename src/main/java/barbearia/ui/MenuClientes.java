package barbearia.ui;

import barbearia.excecao.RecursoNaoEncontradoException;
import barbearia.excecao.ValidacaoException;
import barbearia.modelo.Cliente;
import barbearia.repositorio.ClienteRepositorio;
import java.util.List;
import java.util.Scanner;

public class MenuClientes {
    private final ClienteRepositorio repositorio;
    private final Scanner scanner;

    public MenuClientes(ClienteRepositorio repositorio, Scanner scanner) {
        this.repositorio = repositorio;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n=== MENU CLIENTES ===");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Buscar por nome");
            System.out.println("5 - Atualizar cliente");
            System.out.println("6 - Excluir cliente");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opcao: ");

            String entrada = scanner.nextLine().trim();
            try {
                opcao = Integer.parseInt(entrada);
                executarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido.");
                opcao = -1;
            } catch (ValidacaoException | RecursoNaoEncontradoException e) {
                System.out.println("Erro: " + e.getMessage());
                opcao = -1;
            }
        } while (opcao != 0);
    }

    private void executarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> cadastrar();
            case 2 -> listar();
            case 3 -> buscarPorId();
            case 4 -> buscarPorNome();
            case 5 -> atualizar();
            case 6 -> excluir();
            case 0 -> System.out.println("Voltando...");
            default -> System.out.println("Opcao invalida.");
        }
    }

    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private void cadastrar() {
        int id = lerInteiro("ID (numero): ");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone (com DDD): ");
        String telefone = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        repositorio.salvar(new Cliente(id, nome, telefone, email));
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void listar() {
        List<Cliente> clientes = repositorio.listar();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        clientes.forEach(System.out::println);
    }

    private void buscarPorId() {
        int id = lerInteiro("Informe o ID: ");
        System.out.println(repositorio.buscarPorId(id));
    }

    private void buscarPorNome() {
        System.out.print("Informe o nome ou parte dele: ");
        String nome = scanner.nextLine();
        List<Cliente> encontrados = repositorio.buscarPorNome(nome);
        if (encontrados.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return;
        }
        encontrados.forEach(System.out::println);
    }

    private void atualizar() {
        int id = lerInteiro("ID do cliente a atualizar: ");
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Novo e-mail: ");
        String email = scanner.nextLine();

        repositorio.atualizar(new Cliente(id, nome, telefone, email));
        System.out.println("Cliente atualizado com sucesso!");
    }

    private void excluir() {
        int id = lerInteiro("ID do cliente a excluir: ");
        repositorio.excluir(id);
        System.out.println("Cliente excluido com sucesso!");
    }
}