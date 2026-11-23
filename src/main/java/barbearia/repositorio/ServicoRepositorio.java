package barbearia.repositorio;

import barbearia.modelo.Servico;
import java.util.ArrayList;
import java.util.List;

public class ServicoRepositorio {
    public int proximoId() { return 1; }
    public Servico salvar(Servico servico) { return servico; }
    public Servico buscarPorId(int id) { return null; }
    public List<Servico> listar() { return new ArrayList<>(); }
    public List<Servico> buscarPorNome(String trecho) { return new ArrayList<>(); }
    public Servico atualizar(Servico servico) { return servico; }
    public void excluir(int id) {}
}
