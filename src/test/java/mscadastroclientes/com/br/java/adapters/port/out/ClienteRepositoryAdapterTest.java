package mscadastroclientes.com.br.java.adapters.port.out;

import mscadastroclientes.com.br.java.adapters.database.entities.ClienteEntity;
import mscadastroclientes.com.br.java.core.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteRepositoryAdapterTest {

    private ClienteRepositoryJpa repositorioJpa;
    private ClienteRepositoryAdapter adapter;

    @BeforeEach
    void setup() {
        repositorioJpa = mock(ClienteRepositoryJpa.class);
        adapter = new ClienteRepositoryAdapter(repositorioJpa);
    }

    @Test
    void deveSalvarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Ana");
        cliente.setCpf("12345678900");
        cliente.setDataNascimento(LocalDate.of(1993, 10, 8));
        cliente.setTelefone("81999999999");
        cliente.setEndereco("Rua A, Recife");

        ClienteEntity entitySalva = new ClienteEntity();
        entitySalva.setId(1L);
        entitySalva.setNome(cliente.getNome());
        entitySalva.setCpf(cliente.getCpf());
        entitySalva.setDataNascimento(cliente.getDataNascimento());
        entitySalva.setTelefone(cliente.getTelefone());
        entitySalva.setEndereco(cliente.getEndereco());

        when(repositorioJpa.save(any(ClienteEntity.class))).thenReturn(entitySalva);

        Cliente resultado = adapter.salvar(cliente);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Ana", resultado.getNome());

        verify(repositorioJpa, times(1)).save(any(ClienteEntity.class));
    }

    @Test
    void deveBuscarClientePorIdExistente() {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(1L);
        entity.setNome("João");

        when(repositorioJpa.findById(1L)).thenReturn(Optional.of(entity));

        Optional<Cliente> resultado = adapter.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("João", resultado.get().getNome());
        verify(repositorioJpa, times(1)).findById(1L);
    }

    @Test
    void deveRetornarVazioSeClienteNaoExistir() {
        when(repositorioJpa.findById(1L)).thenReturn(Optional.empty());

        Optional<Cliente> resultado = adapter.buscarPorId(1L);

        assertTrue(resultado.isEmpty());
        verify(repositorioJpa, times(1)).findById(1L);
    }

    @Test
    void deveBuscarTodosClientes() {
        ClienteEntity entity1 = new ClienteEntity();
        entity1.setId(1L);
        entity1.setNome("Maria");

        ClienteEntity entity2 = new ClienteEntity();
        entity2.setId(2L);
        entity2.setNome("Pedro");

        List<ClienteEntity> listaEntities = List.of(entity1, entity2);

        when(repositorioJpa.findAll()).thenReturn(listaEntities);

        List<Cliente> resultado = adapter.buscarTodos();

        assertEquals(2, resultado.size());
        assertEquals("Maria", resultado.get(0).getNome());
        assertEquals("Pedro", resultado.get(1).getNome());

        verify(repositorioJpa, times(1)).findAll();
    }

    @Test
    void deveExcluirClientePorId() {
        Long id = 1L;

        doNothing().when(repositorioJpa).deleteById(id);

        adapter.excluirPorId(id);

        verify(repositorioJpa, times(1)).deleteById(id);
    }
}
