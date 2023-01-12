package com.pessoaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "PESSOA")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaEntity {

    @Id
    @Column(name = "ID_PESSOA")
    @GeneratedValue(generator="SEQUENCE_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="SEQUENCE_GENERATOR",sequenceName="SEQ_PESSOA", allocationSize=100)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL)
    private Set<EnderecoEntity> enderecos;

}
