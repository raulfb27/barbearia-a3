package barbearia.repositorio;

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

    public Servico buscarPorId(int id) { return null; }
    public List<Servico> listar() { return new ArrayList<>(); }
    public List<Servico> buscarPorNome(String trecho) { return new ArrayList<>(); }
    public Servico atualizar(Servico servico) { return servico; }
    public void excluir(int id) {}
}
