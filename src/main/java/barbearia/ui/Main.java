package barbearia.ui;

import barbearia.repositorio.AtendimentoRepositorio;
import barbearia.repositorio.ServicoRepositorio;
import barbearia.servico.FinanceiroService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ServicoRepositorio servicoRepositorio = new ServicoRepositorio();
        AtendimentoRepositorio atendimentoRepositorio = new AtendimentoRepositorio();

        FinanceiroService financeiroService = new FinanceiroService(atendimentoRepositorio);

        MenuServicos menuServicos = new MenuServicos(servicoRepositorio, scanner);
        MenuFinanceiro menuFinanceiro = new MenuFinanceiro(financeiroService, scanner);

        MenuPrincipal menuPrincipal = new MenuPrincipal(menuServicos, menuFinanceiro, scanner);
        menuPrincipal.exibir();

        scanner.close();
    }
}