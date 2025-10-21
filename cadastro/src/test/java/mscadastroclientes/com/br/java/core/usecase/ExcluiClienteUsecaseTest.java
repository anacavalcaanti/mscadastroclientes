package mscadastroclientes.com.br.java.core.usecase;

import mscadastroclientes.com.br.java.core.port.out.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ExcluiClienteUsecaseTest {

    private ClienteRepository repository;
    private ExcluiClienteUsecase usecase;

    @BeforeEach
    void setup() {
        repository = mock(ClienteRepository.class);
        usecase = new ExcluiClienteUsecase(repository);
    }

    @Test
    void deveExcluirClientePorId() {
        Long id = 1L;

        doNothing().when(repository).excluirPorId(id);

        usecase.excluir(id);

        verify(repository, times(1)).excluirPorId(id);
    }
}
