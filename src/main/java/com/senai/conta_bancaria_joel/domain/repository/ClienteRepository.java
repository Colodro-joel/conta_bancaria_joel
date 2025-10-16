package com.senai.conta_bancaria_joel.domain.repository;

import com.senai.conta_bancaria_joel.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
        Optional<Cliente> findByCpfAndAtivoTrue();

        List<Cliente> findAllByAtivoTrue();
};

