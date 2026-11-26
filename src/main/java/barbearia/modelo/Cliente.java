package barbearia.modelo;

import barbearia.excecao.ValidacaoException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Cliente {
    private static final Pattern PADRAO_EMAIL =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private final int id;
    private String nome;
    private String telefone;
    private String email;

    public Cliente(int id, String nome, String telefone, String email) {
        if (id <= 0) {
            throw new ValidacaoException("ID do cliente deve ser maior que zero.");
        }
        this.id = id;
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().length() < 2) {
            throw new ValidacaoException("Nome do cliente deve ter pelo menos 2 caracteres.");
        }
        this.nome = nome.trim();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null) {
            throw new ValidacaoException("Telefone do cliente e obrigatorio.");
        }
        String apenasDigitos = telefone.replaceAll("\\D", "");
        if (apenasDigitos.length() < 10 || apenasDigitos.length() > 11) {
            throw new ValidacaoException("Telefone deve ter 10 ou 11 digitos (com DDD).");
        }
        this.telefone = apenasDigitos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank() || !PADRAO_EMAIL.matcher(email.trim()).matches()) {
            throw new ValidacaoException("E-mail do cliente invalido.");
        }
        this.email = email.trim().toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return id == cliente.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nome='" + nome + "', telefone='" + telefone + "', email='" + email + "'}";
    }
}