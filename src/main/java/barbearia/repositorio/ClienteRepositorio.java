package barbearia.repositorio;

import barbearia.excecao.RecursoNaoEncontradoException;
import barbearia.excecao.ValidacaoException;
import barbearia.modelo.Cliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteRepositorio {
    private final Map<Integer, Cliente> clientes = new HashMap<>();

    public void salvar(Cliente cliente) {
        validarNaoNulo(cliente);
        if (clientes.containsKey(cliente.getId())) {
            throw new ValidacaoException("Ja existe cliente com ID: " + cliente.getId());
        }
        clientes.put(cliente.getId(), cliente);
    }

    public Cliente buscarPorId(int id) {
        validarIdPositivo(id);
        Cliente encontrado = clientes.get(id);
        if (encontrado == null) {
            throw new RecursoNaoEncontradoException("Cliente nao encontrado com ID: " + id);
        }
        return encontrado;
    }

    public List<Cliente> listar() {
        return new ArrayList<>(clientes.values());
    }

    public void atualizar(Cliente cliente) {
        validarNaoNulo(cliente);
        if (!clientes.containsKey(cliente.getId())) {
            throw new RecursoNaoEncontradoException("Cliente nao encontrado para atualizar: " + cliente.getId());
        }
        clientes.put(cliente.getId(), cliente);
    }

    public void excluir(int id) {
        validarIdPositivo(id);
        if (clientes.remove(id) == null) {
            throw new RecursoNaoEncontradoException("Cliente nao encontrado para excluir: " + id);
        }
    }

    public List<Cliente> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new ValidacaoException("Nome para busca e obrigatorio.");
        }
        String termo = nome.trim().toLowerCase();
        List<Cliente> encontrados = new ArrayList<>();
        for (Cliente cliente : clientes.values()) {
            if (cliente.getNome().toLowerCase().contains(termo)) {
                encontrados.add(cliente);
            }
        }
        return encontrados;
    }

    private void validarNaoNulo(Cliente cliente) {
        if (cliente == null) {
            throw new ValidacaoException("Cliente nao pode ser nulo.");
        }
    }

    private void validarIdPositivo(int id) {
        if (id <= 0) {
            throw new ValidacaoException("ID deve ser maior que zero.");
        }
    }
}