package com.senai.conta_bancaria_joel.domain.Exception;

public class TransferirParaMesmaContaException extends RuntimeException{
    public TransferirParaMesmaContaException(){
        super("Operação não permitida");
    }
}
