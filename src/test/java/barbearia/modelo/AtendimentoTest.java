package barbearia.modelo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AtendimentoTest {

    @Test
    void deveCriarAtendimentoComPrecoDoServico() {
        Cliente cliente = new Cliente(1, "Carlos", "11999999999", "carlos@email.com");
        Servico servico = new Servico(10, "Corte", 40.0, 30);
        LocalDate data = LocalDate.of(2026, 11, 26);

        Atendimento atendimento = new Atendimento(1, cliente, servico, data);

        assertEquals(1, atendimento.getId());
        assertEquals("Carlos", atendimento.getCliente().getNome());
        assertEquals("Corte", atendimento.getServico().getNome());
        assertEquals(data, atendimento.getData());
        assertEquals(40.0, atendimento.getValorCobrado());
    }
}