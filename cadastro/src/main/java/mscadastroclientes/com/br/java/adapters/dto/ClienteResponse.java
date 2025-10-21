package mscadastroclientes.com.br.java.adapters.dto;

import mscadastroclientes.com.br.java.core.model.Cliente;

import java.time.LocalDate;

public record ClienteResponse (
        Long id,
        String cpf,
        String nome,
        LocalDate dataNascimento,
        String telefone,
        String endereco
) {
    public static ClienteResponse de(Cliente c) {
        return new ClienteResponse(
                c.getId(),
                c.getCpf(),
                c.getNome(),
                c.getDataNascimento(),
                c.getTelefone(),
                c.getEndereco()
        );
    }
}
