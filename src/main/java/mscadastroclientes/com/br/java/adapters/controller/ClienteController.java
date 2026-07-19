package mscadastroclientes.com.br.java.adapters.controller;

import mscadastroclientes.com.br.java.adapters.dto.ClienteRequest;
import mscadastroclientes.com.br.java.adapters.dto.ClienteResponse;
import mscadastroclientes.com.br.java.core.port.in.AtualizaCadastroInterface;
import mscadastroclientes.com.br.java.core.port.in.BuscaCadastroInterface;
import mscadastroclientes.com.br.java.core.port.in.CriaCadastroInterface;
import mscadastroclientes.com.br.java.core.port.in.ExcluiCadastroInterface;
import mscadastroclientes.com.br.java.core.usecase.AtualizaClienteUsecase;
import mscadastroclientes.com.br.java.core.usecase.BuscaClienteUsecase;
import mscadastroclientes.com.br.java.core.usecase.CadastroClienteUseCase;
import mscadastroclientes.com.br.java.core.usecase.ExcluiClienteUsecase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final CadastroClienteUseCase cadastroClienteUseCase;
    private final BuscaClienteUsecase buscaClienteUseCase;
    private final AtualizaClienteUsecase atualizaClienteUseCase;
    private final ExcluiClienteUsecase excluiClienteUseCase;

    public ClienteController(CadastroClienteUseCase cadastroClienteUseCase, BuscaClienteUsecase buscaClienteUseCase, AtualizaClienteUsecase atualizaClienteUseCase, ExcluiClienteUsecase excluiClienteUseCase) {
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
