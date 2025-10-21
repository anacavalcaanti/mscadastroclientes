package mscadastroclientes.com.br.java.core.usecase;

import mscadastroclientes.com.br.java.core.model.Cliente;
import mscadastroclientes.com.br.java.core.port.in.CriaCadastroInterface;
import mscadastroclientes.com.br.java.core.port.out.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteUseCase implements CriaCadastroInterface {

    private final ClienteRepository repository;

    public CadastroClienteUseCase(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente criar(Cliente cliente) {
        return repository.salvar(cliente);
    }
}

