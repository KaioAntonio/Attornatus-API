package com.pessoaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pessoaapi.dto.pessoa.PessoaCreateDTO;
import com.pessoaapi.dto.pessoa.PessoaDTO;
import com.pessoaapi.entity.PessoaEntity;
import com.pessoaapi.exceptions.RegraDeNegocioException;
import com.pessoaapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final ObjectMapper objectMapper;

    public PessoaDTO create(PessoaCreateDTO pessoaCreateDTO){
        PessoaEntity pessoaEntity = objectMapper.convertValue(pessoaCreateDTO, PessoaEntity.class);
        pessoaEntity.setNome(pessoaCreateDTO.getNome());
        pessoaEntity.setDataNascimento(pessoaCreateDTO.getDataNascimento());

        pessoaRepository.save(pessoaEntity);

        return objectMapper.convertValue(pessoaEntity, PessoaDTO.class);

    }

    public List<PessoaDTO> listAll(){
        return pessoaRepository.findAll()
                .stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .toList();
    }

    public PessoaDTO findByID(Integer id) throws RegraDeNegocioException {
        PessoaEntity pessoaEntity = pessoaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));

        return objectMapper.convertValue(pessoaEntity, PessoaDTO.class);
    }

    public PessoaDTO edit(Integer id, PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException {
        findByID(id);
        PessoaEntity pessoa = objectMapper.convertValue(pessoaCreateDTO, PessoaEntity.class);

        pessoa.setId(id);
        pessoa.setNome(pessoaCreateDTO.getNome());
        pessoa.setDataNascimento(pessoaCreateDTO.getDataNascimento());
        //Como não foi pedido para editar o endereço | Levei em consideração so editar os dados da pessoa.
        pessoaRepository.save(pessoa);

        return objectMapper.convertValue(pessoa, PessoaDTO.class);
    }

}
