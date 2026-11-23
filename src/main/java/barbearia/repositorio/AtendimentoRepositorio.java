package barbearia.repositorio;

import barbearia.modelo.Atendimento;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoRepositorio {
    public int proximoId() { return 1; }
    public Atendimento registrar(Atendimento atendimento) { return atendimento; }
    public Atendimento buscarPorId(int id) { return null; }
    public List<Atendimento> listar() { return new ArrayList<>(); }
    public List<Atendimento> listarPorCliente(int idCliente) { return new ArrayList<>(); }
    public List<Atendimento> listarPorPeriodo(LocalDate inicio, LocalDate fim) { return new ArrayList<>(); }
    public Atendimento atualizar(Atendimento atendimento) { return atendimento; }
    public void excluir(int id) {}
}
