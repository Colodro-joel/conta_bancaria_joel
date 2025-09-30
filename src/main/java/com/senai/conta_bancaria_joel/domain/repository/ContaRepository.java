package com.senai.conta_bancaria_joel.domain.repository;

import com.senai.conta_bancaria_joel.domain.entity.ContaBancária;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<ContaBancária, String> {

    List<ContaBancária> findAllByAtivaTrue();
    Optional<ContaBancária> findByNumeroAndAtivaTrue(String numero);

}

