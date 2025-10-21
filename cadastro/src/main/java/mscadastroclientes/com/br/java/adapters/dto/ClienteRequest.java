package mscadastroclientes.com.br.java.adapters.dto;

import mscadastroclientes.com.br.java.core.model.Cliente;

import java.time.LocalDate;

public record ClienteRequest (
        String cpf,
        String nome,
        LocalDate dataNascimento,
        String telefone,
        String endereco
) {
    public Cliente paraDominio() {
        Cliente c = new Cliente();
        c.setCpf(cpf);
        c.setNome(nome);
        c.setDataNascimento(dataNascimento);
        c.setTelefone(telefone);
        c.setEndereco(endereco);
        return c;
    }
}
