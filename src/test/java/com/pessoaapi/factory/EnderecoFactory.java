package com.pessoaapi.factory;

import com.pessoaapi.dto.endereco.EnderecoCreateDTO;
import com.pessoaapi.entity.EnderecoEntity;

public class EnderecoFactory {

    public static EnderecoEntity getEnderecoEntity() {
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(1);
        enderecoEntity.setPrincipal(false);
        enderecoEntity.setCEP("49700-000");
        enderecoEntity.setNumero(10);
        enderecoEntity.setLogradouro("Rua da paz");
        enderecoEntity.setCidade("Capela");
        enderecoEntity.setIdPessoa(1);
        enderecoEntity.setPessoa(PessoaFactory.getPessoaEntity());
        return enderecoEntity;
    }

    public static EnderecoCreateDTO getEnderecoCreateDTO() {
        EnderecoCreateDTO enderecoCreateDTO = new EnderecoCreateDTO();
        enderecoCreateDTO.setCEP("49700-000");
        enderecoCreateDTO.setNumero(10);
        enderecoCreateDTO.setLogradouro("Rua da paz");
        enderecoCreateDTO.setCidade("Capela");
        enderecoCreateDTO.setIdPessoa(1);
        return enderecoCreateDTO;
    }
}
