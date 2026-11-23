package barbearia.modelo;

public class Servico {
    private int id;
    private String nome;
    private double preco;
    private int duracaoMinutos;

    public Servico(int id, String nome, double preco, int duracaoMinutos) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.duracaoMinutos = duracaoMinutos;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setDuracaoMinutos(int minutos) { this.duracaoMinutos = minutos; }
}
