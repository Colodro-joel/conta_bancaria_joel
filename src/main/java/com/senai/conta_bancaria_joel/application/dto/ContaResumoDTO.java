package com.senai.conta_bancaria_joel.application.dto;

import com.senai.conta_bancaria_joel.domain.entity.Cliente;
import com.senai.conta_bancaria_joel.domain.entity.ContaBancária;
import com.senai.conta_bancaria_joel.domain.entity.ContaCorrente;
import com.senai.conta_bancaria_joel.domain.entity.ContaPoupança;
import com.senai.conta_bancaria_joel.domain.Exception.TipodeContaInvalidaException;

import java.math.BigDecimal;

public record ContaResumoDTO (
    String numero,
    String tipo,
    BigDecimal saldo,
    BigDecimal limite,
    BigDecimal taxa,
    BigDecimal rendimento
) {

    public ContaBancária toEntity(Cliente cliente) {
        if ("CORRENTE".equalsIgnoreCase(tipo)) {
            return ContaCorrente.builder()
                    .cliente(cliente)
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .taxa(new BigDecimal("0.05"))
                    .limite(new BigDecimal("500.00"))
                    .ativa(true)
                    .build();
        } else if ("POUPANCA".equalsIgnoreCase(tipo)) {
            return ContaPoupança.builder()
                    .cliente(cliente)
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .rendimento(new BigDecimal("0.01"))
                    .ativa(true)
                    .build();
        }

        throw new TipodeContaInvalidaException(tipo);
    }

    public static ContaResumoDTO fromEntity (ContaBancária conta){
            return new ContaResumoDTO(
                    conta.getNumero(),
                    conta.getTipo(),
                    conta.getSaldo(),
                    conta instanceof ContaCorrente cc ? cc.getLimite() : null,
                    conta instanceof ContaCorrente cc ? cc.getTaxa() : null,
                    conta instanceof ContaPoupança cp ? cp.getRendimento() : null
            );
    }

}
