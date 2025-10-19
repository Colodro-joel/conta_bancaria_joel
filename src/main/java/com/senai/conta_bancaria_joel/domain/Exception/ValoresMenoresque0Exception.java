package com.senai.conta_bancaria_joel.domain.Exception;

public class ValoresMenoresque0Exception extends RuntimeException {
    public ValoresMenoresque0Exception(String operacao) {
        super("Não é possivel"+ operacao + "valores negativos.");
    }
}
