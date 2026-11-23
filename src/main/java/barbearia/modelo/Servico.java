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
    public double getPreco() { return preco; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setDuracaoMinutos(int minutos) { this.duracaoMinutos = minutos; }
}
