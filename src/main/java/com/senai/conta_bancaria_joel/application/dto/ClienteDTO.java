package com.senai.conta_bancaria_joel.application.dto;

import com.senai.conta_bancaria_joel.domain.entity.ContaBancária;
import com.senai.conta_bancaria_joel.domain.entity.Cliente;

import java.util.List;

public record ClienteDTO(
        String nome,
        Long cpf,
        List<ContaBancária> contas
) {
        public static ClienteDTO fromEntity (Cliente cliente){
        if (cliente == null) return null;
        return new ClienteDTO(
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getContas()
        );
    }
}