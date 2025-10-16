package com.senai.conta_bancaria_joel.application.dto;

import com.senai.conta_bancaria_joel.domain.entity.Cliente;
import com.senai.conta_bancaria_joel.domain.entity.ContaBancária;
import com.senai.conta_bancaria_joel.domain.repository.ClienteRepository;

import java.util.List;

public record ClienteResponseDTO (
    long id,
    String nome,
    String cpf,
    List<ContaResumoDTO> conta
){

    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                conta
        );
    }
}
