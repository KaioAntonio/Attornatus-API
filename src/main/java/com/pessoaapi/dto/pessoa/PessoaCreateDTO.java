package com.pessoaapi.dto.pessoa;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class PessoaCreateDTO {
    @NotBlank(message = "Nome não pode ser vazio ou nulo.")
    @Schema(description = "Nome", example = "Kaio")
    private String nome;

    @NotBlank(message = "Data de nascimento não pode ser vazio ou nulo.")
    @Schema(description = "data de nascimento", example = "2022-03-14")
    private LocalDate dataNascimento;

}
