package barbearia.ui;

import barbearia.repositorio.AtendimentoRepositorio;
import barbearia.repositorio.ClienteRepositorio;
import barbearia.repositorio.ServicoRepositorio;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AtendimentoRepositorio atendimentoRepositorio = new AtendimentoRepositorio();
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
        ServicoRepositorio servicoRepositorio = new ServicoRepositorio();

        MenuAtendimentos menuAtendimentos = new MenuAtendimentos(
                atendimentoRepositorio,
                clienteRepositorio,
                servicoRepositorio,
                scanner
        );

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== SISTEMA BARBEARIA A3 ===");
            System.out.println("1 - Atendimentos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1 -> menuAtendimentos.exibir();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}