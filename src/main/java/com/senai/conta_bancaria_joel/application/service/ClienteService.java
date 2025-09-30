package com.senai.conta_bancaria_joel.application.service;

import com.senai.conta_bancaria_joel.application.dto.ClienteAtualizadoDTO;
import com.senai.conta_bancaria_joel.application.dto.ClienteRegistroDTO;
import com.senai.conta_bancaria_joel.application.dto.ClienteResponseDTO;
import com.senai.conta_bancaria_joel.domain.entity.Cliente;
import com.senai.conta_bancaria_joel.domain.entity.ContaCorrente;
import com.senai.conta_bancaria_joel.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        var novaconta = dto.conta().toEntity(cliente);
        /*criar o metodo quando tiver completo tem que verficar se a abinha aprece*/
        boolean jatemtipo = contas.stream()
                .anyMatch(c     -> c.getClass().equals(novaconta.getClass()) && c.istipoConta()
                );
        if (jaexiste)
            throw new RuntimeException("Cliente desse tipo já está cadastrado");

        cliente.getContas().add(novaconta);

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

    public ClienteResponseDTO atualizarCliente(String cpf, ClienteAtualizadoDTO dto) {
    }

    public void deletarCliente(String cpf) {
        var cliente = repository.findByCpfAndAtivoTrue(cpf).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado")
        );
        cliente.setAtivo(false);
        cliente.getContas().forEach(
                conta ->conta.setAtiva(false)
        );
        repository.save(cliente);
    }
}

