package mscadastroclientes.com.br.java.core.usecase;

import mscadastroclientes.com.br.java.core.model.Cliente;
import mscadastroclientes.com.br.java.core.port.out.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizaClienteUsecaseTest {

    private ClienteRepository repository;
    private AtualizaClienteUsecase usecase;

    @BeforeEach
    void setup() {
        repository = mock(ClienteRepository.class);
        usecase = new AtualizaClienteUsecase(repository);
    }

    @Test
    void deveAtualizarClienteComSucesso() {
        Long id = 1L;

        Cliente existente = new Cliente();
        existente.setId(id);
        existente.setNome("João");
        existente.setCpf("12345678900");
        existente.setDataNascimento(LocalDate.of(1990,1,1));
        existente.setTelefone("81999998888");
        existente.setEndereco("Rua B, Olinda");

        Cliente atualizado = new Cliente();
        atualizado.setNome("João Silva");
        atualizado.setCpf("12345678900");
        atualizado.setDataNascimento(LocalDate.of(1990,1,1));
        atualizado.setTelefone("81999998888");
        atualizado.setEndereco("Rua B, Olinda");

        // Mockando o repository
        when(repository.buscarPorId(id)).thenReturn(Optional.of(existente));
        when(repository.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Cliente resultado = usecase.atualizar(id, atualizado);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("João Silva", resultado.getNome());

        verify(repository, times(1)).buscarPorId(id);
        verify(repository, times(1)).salvar(atualizado);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExiste() {
        Long id = 1L;
        Cliente atualizado = new Cliente();

        when(repository.buscarPorId(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usecase.atualizar(id, atualizado);
        });

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(repository, times(1)).buscarPorId(id);
        verify(repository, never()).salvar(any());
    }
}
