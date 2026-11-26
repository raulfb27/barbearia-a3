package barbearia.repositorio;

import barbearia.excecao.RecursoNaoEncontradoException;
import barbearia.excecao.ValidacaoException;
import barbearia.modelo.Atendimento;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtendimentoRepositorio {
    private final Map<Integer, Atendimento> atendimentos = new HashMap<>();

    public Atendimento registrar(Atendimento atendimento) {
        if (atendimento == null) {
            throw new ValidacaoException("Atendimento é obrigatório.");
        }
        if (atendimento.getId() <= 0) {
            throw new ValidacaoException("ID do atendimento deve ser maior que zero.");
        }
        if (atendimentos.containsKey(atendimento.getId())) {
            throw new ValidacaoException("Já existe atendimento com o ID: " + atendimento.getId());
        }
        atendimentos.put(atendimento.getId(), atendimento);
        return atendimento;
    }

    public Atendimento buscarPorId(int id) {
        Atendimento atendimento = atendimentos.get(id);
        if (atendimento == null) {
            throw new RecursoNaoEncontradoException("Atendimento não encontrado com ID: " + id);
        }
        return atendimento;
    }

    public List<Atendimento> listarPorCliente(int clienteId) {
        List<Atendimento> resultado = new ArrayList<>();
        for (Atendimento a : atendimentos.values()) {
            if (a.getCliente() != null && a.getCliente().getId() == clienteId) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    public List<Atendimento> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null) {
            throw new ValidacaoException("Datas inicial e final são obrigatórias.");
        }
        if (inicio.isAfter(fim)) {
            throw new ValidacaoException("Data inicial não pode ser posterior à data final.");
        }
        List<Atendimento> resultado = new ArrayList<>();
        for (Atendimento a : atendimentos.values()) {
            if (a.getData() != null && !a.getData().isBefore(inicio) && !a.getData().isAfter(fim)) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    public Atendimento atualizar(Atendimento atendimento) {
        if (atendimento == null) {
            throw new ValidacaoException("Atendimento é obrigatório para atualização.");
        }
        if (!atendimentos.containsKey(atendimento.getId())) {
            throw new RecursoNaoEncontradoException(
                    "Atendimento não encontrado para atualização com ID: " + atendimento.getId()
            );
        }
        atendimentos.put(atendimento.getId(), atendimento);
        return atendimento;
    }

    public void excluir(int id) {
        if (atendimentos.remove(id) == null) {
            throw new RecursoNaoEncontradoException("Atendimento não encontrado para exclusão com ID: " + id);
        }
    }
}
