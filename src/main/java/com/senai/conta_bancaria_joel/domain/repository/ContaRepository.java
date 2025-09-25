package com.senai.conta_bancaria_joel.domain.repository;

import java.util.Collection;

public interface ContaRepository {
    Collection<Object> findAllByAtivaTrue();
}
