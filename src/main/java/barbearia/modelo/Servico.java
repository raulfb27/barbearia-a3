package barbearia.modelo;

import barbearia.excecao.ValidacaoException;

public class Servico {

    private final int id;
    private String nome;
    private double preco;
    private int duracaoMinutos;

    public Servico(int id, String nome, double preco, int duracaoMinutos) {
        if (id <= 0) {
            throw new ValidacaoException("O ID do serviço deve ser maior que zero.");
        }
        this.id = id;
        setNome(nome);
        setPreco(preco);
        setDuracaoMinutos(duracaoMinutos);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidacaoException("O nome do serviço não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(int minutos) { this.duracaoMinutos = minutos; }
}
