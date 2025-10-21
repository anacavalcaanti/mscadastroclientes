package mscadastroclientes.com.br.java.adapters.controller;

import mscadastroclientes.com.br.java.adapters.dto.ClienteRequest;
import mscadastroclientes.com.br.java.adapters.dto.ClienteResponse;
import mscadastroclientes.com.br.java.core.model.Cliente;
import mscadastroclientes.com.br.java.core.port.in.AtualizaCadastroInterface;
import mscadastroclientes.com.br.java.core.port.in.BuscaCadastroInterface;
import mscadastroclientes.com.br.java.core.port.in.CriaCadastroInterface;
import mscadastroclientes.com.br.java.core.port.in.ExcluiCadastroInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    private CriaCadastroInterface criaCadastro;
    private BuscaCadastroInterface buscaCadastro;
    private AtualizaCadastroInterface atualizaCadastro;
    private ExcluiCadastroInterface excluiCadastro;
    private ClienteController controller;

    @BeforeEach
    void setup() {
        criaCadastro = mock(CriaCadastroInterface.class);
        buscaCadastro = mock(BuscaCadastroInterface.class);
        atualizaCadastro = mock(AtualizaCadastroInterface.class);
        excluiCadastro = mock(ExcluiCadastroInterface.class);
        controller = new ClienteController(criaCadastro, buscaCadastro, atualizaCadastro, excluiCadastro);
    }

    @Test
    void deveCriarClienteComSucesso() {
        ClienteRequest req = new ClienteRequest("12345678900", "Ana", LocalDate.of(1993, 10, 8), "81999999999", "Rua A, Recife");

        Cliente cliente = req.paraDominio();
        Cliente salvo = new Cliente();
        salvo.setId(1L);
        salvo.setCpf(cliente.getCpf());
        salvo.setNome(cliente.getNome());
        salvo.setDataNascimento(cliente.getDataNascimento());
        salvo.setTelefone(cliente.getTelefone());
        salvo.setEndereco(cliente.getEndereco());

        when(criaCadastro.criar(any())).thenReturn(salvo);

        ClienteResponse resposta = controller.criar(req);

        assertNotNull(resposta);
        assertEquals("Ana", resposta.nome());
        verify(criaCadastro, times(1)).criar(any());
    }

    @Test
    void deveBuscarClientePorId() {
        Long id = 1L;

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setCpf("12345678900");
        cliente.setNome("João");
        cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
        cliente.setTelefone("81999998888");
        cliente.setEndereco("Rua B, Olinda");

        when(buscaCadastro.buscarPorId(id)).thenReturn(cliente);

        ClienteResponse resposta = controller.buscarPorId(id);

        assertNotNull(resposta);
        assertEquals("João", resposta.nome());
        verify(buscaCadastro).buscarPorId(id);
    }

    @Test
    void deveBuscarTodosClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setCpf("11111111111");
        cliente1.setNome("Maria");
        cliente1.setDataNascimento(LocalDate.of(1995, 5, 10));
        cliente1.setTelefone("81999990000");
        cliente1.setEndereco("Rua C, Recife");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setCpf("22222222222");
        cliente2.setNome("Pedro");
        cliente2.setDataNascimento(LocalDate.of(1998, 3, 25));
        cliente2.setTelefone("81999998888");
        cliente2.setEndereco("Rua D, Olinda");

        List<Cliente> clientes = List.of(cliente1, cliente2);

        when(buscaCadastro.buscarTodos()).thenReturn(clientes);

        List<ClienteResponse> resposta = controller.buscarTodos();

        assertEquals(2, resposta.size());
        assertEquals("Maria", resposta.get(0).nome());
        assertEquals("Pedro", resposta.get(1).nome());
        verify(buscaCadastro, times(1)).buscarTodos();
    }

    @Test
    void deveAtualizarCliente() {
        Long id = 1L;
        ClienteRequest req = new ClienteRequest("12345678900", "Carla", LocalDate.of(1999, 6, 20), "81988887777", "Rua E, Paulista");

        Cliente atualizado = new Cliente();
        atualizado.setId(id);
        atualizado.setCpf(req.cpf());
        atualizado.setNome(req.nome());
        atualizado.setDataNascimento(req.dataNascimento());
        atualizado.setTelefone(req.telefone());
        atualizado.setEndereco(req.endereco());

        when(atualizaCadastro.atualizar(eq(id), any())).thenReturn(atualizado);

        ClienteResponse resposta = controller.atualizar(id, req);

        assertNotNull(resposta);
        assertEquals("Carla", resposta.nome());
        verify(atualizaCadastro).atualizar(eq(id), any());
    }

    @Test
    void deveExcluirCliente() {
        Long id = 1L;

        doNothing().when(excluiCadastro).excluir(id);

        controller.excluir(id);

        verify(excluiCadastro, times(1)).excluir(id);
    }
}
