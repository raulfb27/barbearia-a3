package barbearia.ui;

import barbearia.repositorio.AtendimentoRepositorio;
import barbearia.repositorio.ClienteRepositorio;
import barbearia.repositorio.ServicoRepositorio;
import barbearia.servico.FinanceiroService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
        ServicoRepositorio servicoRepositorio = new ServicoRepositorio();
        AtendimentoRepositorio atendimentoRepositorio = new AtendimentoRepositorio();

        FinanceiroService financeiroService = new FinanceiroService(atendimentoRepositorio);

        MenuClientes menuClientes = new MenuClientes(clienteRepositorio, scanner);
        MenuServicos menuServicos = new MenuServicos(servicoRepositorio, scanner);
        MenuAtendimentos menuAtendimentos = new MenuAtendimentos(
                atendimentoRepositorio,
                clienteRepositorio,
                servicoRepositorio,
                scanner
        );
        MenuFinanceiro menuFinanceiro = new MenuFinanceiro(financeiroService, scanner);

        MenuPrincipal menuPrincipal = new MenuPrincipal(
                menuClientes,
                menuServicos,
                menuAtendimentos,
                menuFinanceiro,
                scanner
        );
        menuPrincipal.exibir();

        scanner.close();
    }
}