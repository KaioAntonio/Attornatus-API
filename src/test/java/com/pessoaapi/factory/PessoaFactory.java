package com.pessoaapi.factory;

import com.pessoaapi.dto.pessoa.PessoaCreateDTO;
import com.pessoaapi.dto.pessoa.PessoaDTO;
import com.pessoaapi.entity.EnderecoEntity;
import com.pessoaapi.entity.PessoaEntity;

import java.time.LocalDate;

public class PessoaFactory {

    public static PessoaEntity getPessoaEntity() {
        PessoaEntity pessoaEntity = new PessoaEntity();
        pessoaEntity.setId(1);
        pessoaEntity.setNome("Kaio");
        pessoaEntity.setDataNascimento(LocalDate.of(2022,03,14));
        return pessoaEntity;
    }

    public static PessoaCreateDTO getPessoaCreateDTO() {
        PessoaCreateDTO pessoaCreateDTO = new PessoaCreateDTO();
        pessoaCreateDTO.setNome("Kaio");
        pessoaCreateDTO.setDataNascimento(LocalDate.of(2022,03,14));
        return pessoaCreateDTO;

    }

    public static PessoaDTO getPessoaDTO() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(1);
        pessoaDTO.setNome("Kaio");
        pessoaDTO.setDataNascimento(LocalDate.of(2022,03,14));
        return pessoaDTO;

    }


}
