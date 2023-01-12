package com.pessoaapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "ENDERECO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoEntity {

    @Id
    @Column(name = "ID_ENDERECO")
    @GeneratedValue(generator="SEQUENCE_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="SEQUENCE_GENERATOR",sequenceName="SEQ_ENDERECO", allocationSize=100)
    private Integer id;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "CEP")
    private String CEP;

    @Column(name = "NUMERO")
    private Integer numero;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "PRINCIPAL")
    private Boolean principal;

    @Column(name = "ID_PESSOA",  insertable = false, updatable = false)
    private Integer idPessoa;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID_PESSOA")
    private PessoaEntity pessoa;

}
