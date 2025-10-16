package com.senai.conta_bancaria_joel.domain.Exception;

import com.senai.conta_bancaria_joel.domain.entity.ContaBancária;

public class EntidadeNãoEncontradaException extends RuntimeException {
    public EntidadeNãoEncontradaException(String entidade) {
        super(entidade + "Não Encontrada");
    }
}
