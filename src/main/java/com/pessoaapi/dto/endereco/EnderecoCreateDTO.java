package com.pessoaapi.dto.endereco;

import lombok.Data;

@Data
public class EnderecoCreateDTO {

    private String logradouro;

    private String CEP;

    private Integer numero;

    private String cidade;

    private Integer idPessoa;
}
