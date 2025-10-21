package mscadastroclientes.com.br.java.core.port.in;

import mscadastroclientes.com.br.java.core.model.Cliente;

import java.util.List;

public interface BuscaCadastroInterface {
    Cliente buscarPorId(Long id);
    List<Cliente> buscarTodos();
}
