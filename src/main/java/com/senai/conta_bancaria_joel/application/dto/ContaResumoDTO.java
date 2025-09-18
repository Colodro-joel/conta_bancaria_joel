package com.senai.conta_bancaria_joel.application.dto;

import com.senai.conta_bancaria_joel.domain.entity.Cliente;
import com.senai.conta_bancaria_joel.domain.entity.ContaCorrente;

import java.math.BigDecimal;

public class ContaResumoDTO (
    String nomeCliente;
    String numeroConta;
    BigDecimal saldo;
){

    public Conta toEntity(Cliente cliente) {
        if("CORRENTE".equalsIgnoreCase(tipo)) {
            return new ContaCorrente(cliente);
        } else if ("POUPANCA".equals(tipoConta())) {
            return new ContaPoupanca(cliente);
        }
        return tipoConta().equals(ContaCorrente.class) ?
                new ContaCorrente(cliente) : new ContaPoupanca(cliente);
    }
}
