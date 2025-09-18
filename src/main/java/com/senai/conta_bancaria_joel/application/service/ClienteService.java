package com.senai.conta_bancaria_joel.application.service;

import com.senai.conta_bancaria_joel.application.dto.ClienteRegistroDTO;
import com.senai.conta_bancaria_joel.application.dto.ClienteResponseDTO;
import com.senai.conta_bancaria_joel.domain.entity.Cliente;
import com.senai.conta_bancaria_joel.domain.entity.ContaCorrente;
import com.senai.conta_bancaria_joel.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
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
                .anyMatch(c     -> c.getClass().equals(dto.conta().tipoConta()));


        return null;
    }
}
