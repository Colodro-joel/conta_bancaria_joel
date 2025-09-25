package com.senai.conta_bancaria_joel.domain.repository;

import java.lang.ScopedValue;

public interface ClienteRepository {
    <T> ScopedValue<T> findByCpfAndAtivoTrue(String cpf);
}
