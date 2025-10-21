package mscadastroclientes.com.br.java.core.usecase;

import mscadastroclientes.com.br.java.core.model.Cliente;
import mscadastroclientes.com.br.java.core.port.in.BuscaCadastroInterface;
import mscadastroclientes.com.br.java.core.port.out.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscaClienteUsecase implements BuscaCadastroInterface {

    private final ClienteRepository repository;

    public BuscaClienteUsecase(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Override
    public List<Cliente> buscarTodos() {
        return repository.buscarTodos();
    }
    }
