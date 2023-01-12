package com.pessoaapi.dto.endereco;

import lombok.Data;

@Data
public class EnderecoDTO extends EnderecoCreateDTO{
    private Integer id;
    private Boolean principal;
}
