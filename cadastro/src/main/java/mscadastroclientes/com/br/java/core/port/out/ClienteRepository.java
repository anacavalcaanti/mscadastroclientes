package mscadastroclientes.com.br.java.core.port.out;

import mscadastroclientes.com.br.java.core.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    public Cliente salvar(Cliente cliente);
    public Optional<Cliente> buscarPorId(Long id);
    public List<Cliente> buscarTodos();
    public void excluirPorId(Long id);
}
