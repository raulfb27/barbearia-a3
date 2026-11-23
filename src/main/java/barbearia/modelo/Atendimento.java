package barbearia.modelo;

import java.time.LocalDate;

public class Atendimento {
    private int id;
    private Cliente cliente;
    private Servico servico;
    private LocalDate data;
    private double valorCobrado;

    public Atendimento(int id, Cliente cliente, Servico servico, LocalDate data) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.data = data;
        this.valorCobrado = (servico != null) ? servico.getPreco() : 0.0;
    }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Servico getServico() { return servico; }
    public LocalDate getData() { return data; }
    public double getValorCobrado() { return valorCobrado; }
    public void setData(LocalDate data) { this.data = data; }
    public void setValorCobrado(double valor) { this.valorCobrado = valor; }
}
