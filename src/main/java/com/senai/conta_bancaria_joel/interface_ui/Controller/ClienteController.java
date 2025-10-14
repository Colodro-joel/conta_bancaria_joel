package com.senai.conta_bancaria_joel.interface_ui.Controller;

import com.senai.conta_bancaria_joel.application.dto.ClienteAtualizadoDTO;
import com.senai.conta_bancaria_joel.application.dto.ClienteRegistroDTO;
import com.senai.conta_bancaria_joel.application.dto.ClienteResponseDTO;
import com.senai.conta_bancaria_joel.application.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

import java.net.URI;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> registrarCliente(@RequestBody ClienteRegistroDTO dto) {
        ClienteResponseDTO novoCliente = service.registrarCliente(dto);

        return ResponseEntity.created(URI.create("/api/cliente/cpf/"+novoCliente.cpf())
        ).body(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientesAtivos(){
        return ResponseEntity.ok((List<ClienteResponseDTO>) service.listarClientesAtivos());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ResponseEntity<List<ClienteResponseDTO>>> buscarClienteAtivoPorCpf(@PathVariable String cpf){
        return ResponseEntity.ok(service.buscarClienteAtivoPorCpf(cpf));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable String cpf,
                                                               @RequestBody ClienteAtualizadoDTO dto) {
        ClienteResponseDTO clienteAtualizado = service.atualizarCliente(cpf, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpf){
        service.deletarCliente(cpf);
        return ResponseEntity.noContent().build();
    }
}






