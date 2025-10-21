package mscadastroclientes.com.br.java.adapters.controller;

import mscadastroclientes.com.br.java.adapters.dto.ClienteRequest;
import mscadastroclientes.com.br.java.adapters.dto.ClienteResponse;
import mscadastroclientes.com.br.java.core.port.in.AtualizaCadastroInterface;
import mscadastroclientes.com.br.java.core.port.in.BuscaCadastroInterface;
import mscadastroclientes.com.br.java.core.port.in.CriaCadastroInterface;
import mscadastroclientes.com.br.java.core.port.in.ExcluiCadastroInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final CriaCadastroInterface cadastroClienteUseCase;
    private final BuscaCadastroInterface buscaClienteUseCase;
    private final AtualizaCadastroInterface atualizaClienteUseCase;
    private final ExcluiCadastroInterface excluiClienteUseCase;

    public ClienteController(CriaCadastroInterface cadastroClienteUseCase, BuscaCadastroInterface buscaClienteUseCase, AtualizaCadastroInterface atualizaClienteUseCase, ExcluiCadastroInterface excluiClienteUseCase) {
        this.cadastroClienteUseCase = cadastroClienteUseCase;
        this.buscaClienteUseCase = buscaClienteUseCase;
        this.atualizaClienteUseCase = atualizaClienteUseCase;
        this.excluiClienteUseCase = excluiClienteUseCase;
    }

    @PostMapping("/criar")
    public ClienteResponse criar(@RequestBody ClienteRequest req) {
        return ClienteResponse.de(cadastroClienteUseCase.criar(req.paraDominio()));
    }

    @GetMapping("/buscar/{id}")
    public ClienteResponse buscarPorId(@PathVariable Long id) {
        return ClienteResponse.de(buscaClienteUseCase.buscarPorId(id));
    }

    @GetMapping("/buscar-todos")
    public List<ClienteResponse> buscarTodos() {
        return buscaClienteUseCase.buscarTodos()
                .stream()
                .map(ClienteResponse::de)
                .toList();
    }

    @PutMapping("/atualizar/{id}")
    public ClienteResponse atualizar(@PathVariable Long id, @RequestBody ClienteRequest req) {
        return ClienteResponse.de(atualizaClienteUseCase.atualizar(id, req.paraDominio()));
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable Long id) {
        excluiClienteUseCase.excluir(id);
    }
}
