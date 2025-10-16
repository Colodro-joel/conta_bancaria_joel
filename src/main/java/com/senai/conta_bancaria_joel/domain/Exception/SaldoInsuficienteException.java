package com.senai.conta_bancaria_joel.domain.Exception;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String operacao) {
        super("Saldo insuficiente");
    }
}
