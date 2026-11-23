package barbearia.modelo;

import static org.junit.jupiter.api.Assertions.*;

import barbearia.excecao.ValidacaoException;
import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    void deveCriarClienteValidoENormalizarCampos() {
        Cliente cliente = new Cliente(1, "  Joao Silva  ", "(11) 98765-4321", "  JOAO@EMAIL.COM ");

        assertEquals(1, cliente.getId());
        assertEquals("Joao Silva", cliente.getNome());
        assertEquals("11987654321", cliente.getTelefone());
        assertEquals("joao@email.com", cliente.getEmail());
    }

    @Test
    void deveAceitarNomeComLimiteMinimoDeDoisCaracteres() {
        Cliente cliente = new Cliente(2, "Li", "1133445566", "li@barbearia.com");
        assertEquals("Li", cliente.getNome());
    }

    @Test
    void deveLancarExcecaoParaNomeInvalidoOuCurto() {
        assertThrows(ValidacaoException.class, () -> new Cliente(3, "A", "11999999999", "a@b.com"));
        assertThrows(ValidacaoException.class, () -> new Cliente(3, "   ", "11999999999", "a@b.com"));
        assertThrows(ValidacaoException.class, () -> new Cliente(3, null, "11999999999", "a@b.com"));
    }

    @Test
    void deveAceitarTelefoneCom10E11DigitosLimites() {
        Cliente fixo10 = new Cliente(4, "Carlos", "1140028922", "carlos@teste.com");
        Cliente mov11 = new Cliente(5, "Bruno", "11940028922", "bruno@teste.com");

        assertEquals("1140028922", fixo10.getTelefone());
        assertEquals("11940028922", mov11.getTelefone());
    }

    @Test
    void deveLancarExcecaoParaTelefoneForaDosLimitesOuNulo() {
        assertThrows(ValidacaoException.class, () -> new Cliente(6, "Lucas", "123456789", "lucas@teste.com"));
        assertThrows(ValidacaoException.class, () -> new Cliente(7, "Lucas", "123456789012", "lucas@teste.com"));
        assertThrows(ValidacaoException.class, () -> new Cliente(8, "Lucas", null, "lucas@teste.com"));
    }

    @Test
    void deveAceitarEmailValidoELancarExcecaoParaEmailInvalido() {
        Cliente valido = new Cliente(9, "Pedro", "21988887777", "pedro.santos@dominio.com.br");
        assertEquals("pedro.santos@dominio.com.br", valido.getEmail());

        assertThrows(ValidacaoException.class, () -> new Cliente(10, "Pedro", "21988887777", "pedro.com"));
        assertThrows(ValidacaoException.class, () -> new Cliente(11, "Pedro", "21988887777", "   "));
        assertThrows(ValidacaoException.class, () -> new Cliente(12, "Pedro", "21988887777", null));
    }

    @Test
    void deveAtualizarCamposComSettersEValidarNovosValores() {
        Cliente cliente = new Cliente(13, "Rafael", "31999998888", "rafael@teste.com");
        cliente.setNome("Rafael Costa");
        cliente.setTelefone("(31) 97777-6666");
        cliente.setEmail("novo.rafael@teste.com");

        assertEquals("Rafael Costa", cliente.getNome());
        assertEquals("31977776666", cliente.getTelefone());
        assertEquals("novo.rafael@teste.com", cliente.getEmail());

        assertThrows(ValidacaoException.class, () -> cliente.setEmail("invalido"));
    }
}