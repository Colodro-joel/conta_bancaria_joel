package com.senai.conta_bancaria_joel.application.dto;

import com.senai.conta_bancaria_joel.domain.entity.Cliente;
import com.senai.conta_bancaria_joel.domain.entity.ContaBancária;

import java.util.ArrayList;

public class ClienteRegistroDTO(
    String nome,
    String cpf,
    ContaResumoDTO conta
) {

    public Cliente toEntity() {
        return Cliente.builder().
                ativo(true)
                .nome(this.nome)
                .cpf(this.cpf)
                .contas(new ArrayList<ContaBancária>())
                .build();
    }

}
