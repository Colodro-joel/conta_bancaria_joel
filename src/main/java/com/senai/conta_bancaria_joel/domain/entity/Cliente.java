package com.senai.conta_bancaria_joel.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cliente",
        uniqueConstraints = {
                @UniqueConstraint( columnNames = "cpf")
        }
)
public  class Cliente {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;

        @Column(nullable = false, length = 120)
        private String nome;

        @Column(nullable = false, length = 11)
        private String cpf;

        @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
        private List<ContaBancária> contas;

        @Column(nullable = false)
        private Boolean ativo;
}

