package barbearia.repositorio;

import barbearia.excecao.RecursoNaoEncontradoException;
import barbearia.excecao.ValidacaoException;
import barbearia.modelo.Servico;
import java.util.ArrayList;
import java.util.List;

public class ServicoRepositorio {

    private final List<Servico> servicos = new ArrayList<>();
    private int sequenciaId = 1;

    public int proximoId() {
        return sequenciaId;
    }

    public Servico salvar(Servico servico) {
        if (servico == null) {
            throw new ValidacaoException("O serviço não pode ser nulo.");
        }
        for (Servico existente : servicos) {
            if (existente.getId() == servico.getId()) {
                throw new ValidacaoException("Já existe um serviço cadastrado com o ID " + servico.getId());
            }
        }
        servicos.add(servico);
        if (servico.getId() >= sequenciaId) {
            sequenciaId = servico.getId() + 1;
        }
        return servico;
    }

    public Servico buscarPorId(int id) {
        for (Servico servico : servicos) {
            if (servico.getId() == id) {
                return servico;
            }
        }
        throw new RecursoNaoEncontradoException("Serviço não encontrado com o ID: " + id);
    }

    public List<Servico> listar() {
        return new ArrayList<>(servicos);
    }

    public List<Servico> buscarPorNome(String trecho) {
        List<Servico> resultado = new ArrayList<>();
        if (trecho == null || trecho.trim().isEmpty()) {
            return resultado;
        }
        String filtro = trecho.trim().toLowerCase();
        for (Servico servico : servicos) {
            if (servico.getNome().toLowerCase().contains(filtro)) {
                resultado.add(servico);
            }
        }
        return resultado;
    }

    public Servico atualizar(Servico servico) { return servico; }
    public void excluir(int id) {}
}
