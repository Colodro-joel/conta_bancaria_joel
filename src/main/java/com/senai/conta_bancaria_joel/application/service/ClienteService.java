package com.senai.conta_bancaria_joel.application.service;

import com.senai.conta_bancaria_joel.application.dto.ClienteAtualizadoDTO;
import com.senai.conta_bancaria_joel.application.dto.ClienteRegistroDTO;
import com.senai.conta_bancaria_joel.application.dto.ClienteResponseDTO;
import com.senai.conta_bancaria_joel.domain.Exception.EntidadeNãoEncontradaException;
import com.senai.conta_bancaria_joel.domain.entity.Cliente;
import com.senai.conta_bancaria_joel.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteResponseDTO registrarCliente(ClienteRegistroDTO dto) {
        // Lógica para registrar o cliente
        // Por enquanto, retornamos um DTO de resposta vazio

        var cliente = repository.findByCpfAndAtivoTrue(dto.cpf()).orElseGet(
            () -> repository.save(dto.toEntity())
        ); /* or else */
        var contas = cliente.getContas();
        var novaConta = dto.contaDTO().toEntity(cliente);
        /*criar o metodo quando tiver completo tem que verficar se a abinha aprece*/
        boolean jatemtipo = contas.stream()
                .anyMatch(c     -> c.getClass().equals(novaConta.getClass()) && c.getAtiva()
                );
        if (jatemtipo)
            throw new RuntimeException("Cliente desse tipo já está cadastrado");

        cliente.getContas().add(novaConta);

        return ClienteResponseDTO.fromEntity(repository.save(cliente));;

    }

        @GetMapping
        public ResponseEntity<ClienteResponseDTO> listarClientesAtivos() {
            return repository;
        }

        @GetMapping
        public ResponseEntity<List<ClienteResponseDTO> buscarClienteAtivoPorCpf(@PathVariable String cpf) {
            return ResponseEntity.ok(service.buscarClienteAtivoPorCpf(cpf));
        }

    public ClienteRepository atualizarCliente(String cpf, ClienteAtualizadoDTO dto) {
        var cliente = buscarClientePorCpfAtivo(cpf);
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());

        return ClienteResponseDTO.fromEntity(repository.save(cliente));
    }

    public void deletarCliente(String cpf) {
        var cliente = buscarClientePorCpfAtivo(cpf);
        cliente.setAtivo(false);
        cliente.getContas().forEach(
                contaBancária ->contaBancária.setAtiva(false)
        );
        repository.save(cliente);
    }

    private Cliente buscarClientePorCpfAtivo(String cpf) {
        var cliente = repository.findByCpfAndAtivoTrue(cpf).orElseThrow(
                () -> new EntidadeNãoEncontradaException("cliente")
        );
        return cliente;
    }
}

