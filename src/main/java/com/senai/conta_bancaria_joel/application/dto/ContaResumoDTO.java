package com.senai.conta_bancaria_joel.application.dto;

import com.senai.conta_bancaria_joel.domain.entity.Cliente;
import com.senai.conta_bancaria_joel.domain.entity.ContaBancária;
import com.senai.conta_bancaria_joel.domain.entity.ContaCorrente;

import java.math.BigDecimal;

public record ContaResumoDTO (
    String nomeCliente,
    String numeroConta,
    BigDecimal saldo
) {

    public ContaBancária toEntity(Cliente cliente) {
        if ("CORRENTE".equalsIgnoreCase(tipo)) {
            return ContaCorrente.builder()
                    .cliente(cliente)
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativa(true)
                    .build();
        } else if ("POUPANCA".equalsIgnoreCase(tipo)) {
            return ContaCorrente.builder()
                    .cliente(cliente)
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .ativa(true)
                    .build();
            return null;
        }

    }

    public static ContaResumoDTO fromEntity (Conta conta){
            return new ContaResumoDTO(
                    conta.getNumero(),
                    conta.getTipo(),
                    conta.getSaldo()
            );
    }
}
