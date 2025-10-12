package com.senai.conta_bancaria_joel.application.dto;

import com.senai.conta_bancaria_joel.domain.entity.Cliente;
import com.senai.conta_bancaria_joel.domain.entity.ContaBancária;
import com.senai.conta_bancaria_joel.domain.entity.ContaCorrente;
import com.senai.conta_bancaria_joel.domain.entity.ContaPoupança;

import java.math.BigDecimal;

public record ContaResumoDTO (
    String nomeCliente,
    String numeroConta,
    BigDecimal saldo,
    String tipo
) {

    public ContaBancária toEntity(Cliente cliente) {
        if ("CORRENTE".equalsIgnoreCase(tipo)) {
            return ContaCorrente.builder()
                    .cliente(cliente)
                    .numero(this.numeroConta)
                    .saldo(this.saldo)
                    .taxa(new BigDecimal(0.05))
                    .limite(new BigDecimal(500.00))
                    .ativa(true)
                    .build();
        } else if ("POUPANCA".equalsIgnoreCase(tipo)) {
            return ContaPoupança.builder()
                    .cliente(cliente)
                    .numero(this.numeroConta)
                    .saldo(this.saldo)
                    .rendimento(new BigDecimal(0.01))
                    .ativa(true)
                    .build();
        }

        return null;
    }

    public static ContaResumoDTO fromEntity (ContaResumoDTO conta){
            return new ContaResumoDTO(
                    conta.getNumero(),
                    conta.getSaldo(),
                    conta.getTipo()
            );
    }

}
