package com.pessoaapi.dto.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EnderecoCreateDTO {

    @NotBlank(message = "Logradouro não pode ser vazio ou nulo.")
    @Schema(description = "Logradouro", example = "Rua da paz")
    private String logradouro;

    @NotBlank(message = "CEP não pode ser vazio ou nulo.")
    @Schema(description = "CEP", example = "49700-000")
    private String CEP;

    @NotBlank(message = "Numero não pode ser vazio ou nulo.")
    @Schema(description = "Numero", example = "10")
    private Integer numero;

    @NotBlank(message = "Cidade não pode ser vazio ou nulo.")
    @Schema(description = "Cidade", example = "Aracaju")
    private String cidade;

    @NotBlank(message = "Id da pessoa não pode ser vazio ou nulo.")
    @Schema(description = "Id da pessoa", example = "1")
    private Integer idPessoa;
}
