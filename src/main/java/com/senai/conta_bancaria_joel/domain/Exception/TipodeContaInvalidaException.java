package com.senai.conta_bancaria_joel.domain.Exception;

public class TipodeContaInvalidaException extends RuntimeException {
    public TipodeContaInvalidaException(String tipo) {
        super(tipo + " é um tipo de conta não aplicada.");
    }
}
