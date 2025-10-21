package mscadastroclientes.com.br.java.core.usecase;

import mscadastroclientes.com.br.java.core.model.Cliente;
import mscadastroclientes.com.br.java.core.port.out.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscaClienteUsecaseTest {

    private ClienteRepository repository;
    private BuscaClienteUsecase usecase;

    @BeforeEach
    void setup() {
        repository = mock(ClienteRepository.class);
        usecase = new BuscaClienteUsecase(repository);
    }

    @Test
    void deveBuscarClientePorIdComSucesso() {
        Long id = 1L;

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome("Ana");
        cliente.setCpf("12345678900");
        cliente.setDataNascimento(LocalDate.of(1993, 10, 8));
        cliente.setTelefone("81999999999");
        cliente.setEndereco("Rua A, Recife");

        when(repository.buscarPorId(id)).thenReturn(Optional.of(cliente));

        Cliente resultado = usecase.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNome());
        verify(repository, times(1)).buscarPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExistir() {
        Long id = 2L;

        when(repository.buscarPorId(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usecase.buscarPorId(id);
        });

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(repository, times(1)).buscarPorId(id);
    }

    @Test
    void deveBuscarTodosClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Maria");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Pedro");

        List<Cliente> clientes = List.of(cliente1, cliente2);

        when(repository.buscarTodos()).thenReturn(clientes);

        List<Cliente> resultado = usecase.buscarTodos();

        assertEquals(2, resultado.size());
        assertEquals("Maria", resultado.get(0).getNome());
        assertEquals("Pedro", resultado.get(1).getNome());
        verify(repository, times(1)).buscarTodos();
    }
}
