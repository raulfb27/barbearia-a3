package barbearia.repositorio;

import barbearia.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio {
    public int proximoId() { return 1; }
    public Cliente salvar(Cliente cliente) { return cliente; }
    public Cliente buscarPorId(int id) { return null; }
    public List<Cliente> listar() { return new ArrayList<>(); }
    public List<Cliente> buscarPorNome(String trecho) { return new ArrayList<>(); }
    public Cliente atualizar(Cliente cliente) { return cliente; }
    public void excluir(int id) {}
}
