package barbearia.ui;

import java.util.Scanner;

public class MenuPrincipal {

    private final MenuClientes menuClientes;
    private final MenuServicos menuServicos;
    private final MenuAtendimentos menuAtendimentos;
    private final MenuFinanceiro menuFinanceiro;
    private final Scanner scanner;

    public MenuPrincipal(
            MenuClientes menuClientes,
            MenuServicos menuServicos,
            MenuAtendimentos menuAtendimentos,
            MenuFinanceiro menuFinanceiro,
            Scanner scanner
    ) {
        this.menuClientes = menuClientes;
        this.menuServicos = menuServicos;
        this.menuAtendimentos = menuAtendimentos;
        this.menuFinanceiro = menuFinanceiro;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===============================");
            System.out.println("     SISTEMA BARBEARIA A3");
            System.out.println("===============================");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Serviços");
            System.out.println("3. Gerenciar Atendimentos");
            System.out.println("4. Relatórios Financeiros");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1 -> menuClientes.exibir();
                case 2 -> menuServicos.exibir();
                case 3 -> menuAtendimentos.exibir();
                case 4 -> menuFinanceiro.exibir();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}