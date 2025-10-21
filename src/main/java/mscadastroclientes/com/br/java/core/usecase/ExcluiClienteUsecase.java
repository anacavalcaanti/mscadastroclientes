package mscadastroclientes.com.br.java.core.usecase;

import mscadastroclientes.com.br.java.core.port.in.ExcluiCadastroInterface;
import mscadastroclientes.com.br.java.core.port.out.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ExcluiClienteUsecase implements ExcluiCadastroInterface {

    private final ClienteRepository repository;

    public ExcluiClienteUsecase(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void excluir(Long id) {
        repository.excluirPorId(id);
    }
}
