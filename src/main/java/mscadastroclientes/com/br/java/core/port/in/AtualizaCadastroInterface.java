package mscadastroclientes.com.br.java.core.port.in;

import mscadastroclientes.com.br.java.core.model.Cliente;

public interface AtualizaCadastroInterface {
    Cliente atualizar(Long id, Cliente cliente);
}
