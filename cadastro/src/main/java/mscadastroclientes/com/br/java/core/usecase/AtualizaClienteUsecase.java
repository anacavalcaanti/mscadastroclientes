package mscadastroclientes.com.br.java.core.usecase;

import mscadastroclientes.com.br.java.core.model.Cliente;
import mscadastroclientes.com.br.java.core.port.in.AtualizaCadastroInterface;
import mscadastroclientes.com.br.java.core.port.out.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizaClienteUsecase implements AtualizaCadastroInterface {

    private final ClienteRepository repository;

    public AtualizaClienteUsecase(ClienteRepository repository) {
        this.repository = repository;
    }


    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente existente = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setId(existente.getId());
        return repository.salvar(cliente);
    }
}