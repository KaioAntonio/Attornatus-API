package com.pessoaapi.repository;

import com.pessoaapi.dto.endereco.EnderecoDTO;
import com.pessoaapi.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {

    List<EnderecoEntity> findEnderecoEntityByIdPessoa(Integer idPessoa);
}
