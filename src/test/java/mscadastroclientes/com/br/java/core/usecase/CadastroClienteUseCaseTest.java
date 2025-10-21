package mscadastroclientes.com.br.java.core.usecase;

import mscadastroclientes.com.br.java.core.model.Cliente;
import mscadastroclientes.com.br.java.core.port.out.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastroClienteUseCaseTest {

    private ClienteRepository repository;
    private CadastroClienteUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(ClienteRepository.class);
        useCase = new CadastroClienteUseCase(repository);
    }

    @Test
    void deveCriarClienteComSucesso() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");
        cliente.setNome("Ana");
        cliente.setDataNascimento(LocalDate.of(1993, 10, 8));
        cliente.setTelefone("81999999999");
        cliente.setEndereco("Rua A, Recife");

        Cliente salvo = new Cliente();
        salvo.setId(1L); // Simula ID gerado pelo repositório
        salvo.setCpf(cliente.getCpf());
        salvo.setNome(cliente.getNome());
        salvo.setDataNascimento(cliente.getDataNascimento());
        salvo.setTelefone(cliente.getTelefone());
        salvo.setEndereco(cliente.getEndereco());

        when(repository.salvar(cliente)).thenReturn(salvo);

        Cliente resultado = useCase.criar(cliente);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Ana", resultado.getNome());

        verify(repository, times(1)).salvar(cliente);
    }
}
