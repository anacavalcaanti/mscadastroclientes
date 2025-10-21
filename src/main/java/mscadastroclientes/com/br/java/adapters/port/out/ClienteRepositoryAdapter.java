package mscadastroclientes.com.br.java.adapters.port.out;

import mscadastroclientes.com.br.java.adapters.database.entities.ClienteEntity;
import mscadastroclientes.com.br.java.core.model.Cliente;
import mscadastroclientes.com.br.java.core.port.out.ClienteRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteRepositoryAdapter implements ClienteRepository {

    private final ClienteRepositoryJpa repositorioJpa;

    public ClienteRepositoryAdapter(ClienteRepositoryJpa repositorioJpa) {
        this.repositorioJpa = repositorioJpa;
    }

    private Cliente paraDominio(ClienteEntity e) {
        Cliente c = new Cliente();
        c.setId(e.getId());
        c.setCpf(e.getCpf());
        c.setNome(e.getNome());
        c.setDataNascimento(e.getDataNascimento());
        c.setTelefone(e.getTelefone());
        c.setEndereco(e.getEndereco());
        return c;
    }

    private ClienteEntity paraEntidade(Cliente c) {
        ClienteEntity e = new ClienteEntity();
        e.setId(c.getId());
        e.setCpf(c.getCpf());
        e.setNome(c.getNome());
        e.setDataNascimento(c.getDataNascimento());
        e.setTelefone(c.getTelefone());
        e.setEndereco(c.getEndereco());
        return e;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return paraDominio(repositorioJpa.save(paraEntidade(cliente)));
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return repositorioJpa.findById(id).map(this::paraDominio);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return repositorioJpa.findAll().stream().map(this::paraDominio).toList();
    }

    @Override
    public void excluirPorId(Long id) {
        repositorioJpa.deleteById(id);
    }
}

