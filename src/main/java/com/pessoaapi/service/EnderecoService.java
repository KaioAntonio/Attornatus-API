package com.pessoaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pessoaapi.dto.endereco.EnderecoCreateDTO;
import com.pessoaapi.dto.endereco.EnderecoDTO;
import com.pessoaapi.dto.pessoa.PessoaDTO;
import com.pessoaapi.entity.EnderecoEntity;
import com.pessoaapi.entity.PessoaEntity;
import com.pessoaapi.exceptions.RegraDeNegocioException;
import com.pessoaapi.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;
    private final PessoaService pessoaService;

    public EnderecoDTO create(EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        PessoaDTO pessoaDTO = pessoaService.findByID(enderecoCreateDTO.getIdPessoa());
        PessoaEntity pessoa = objectMapper.convertValue(pessoaDTO, PessoaEntity.class);
        EnderecoEntity endereco = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);

        endereco.setPrincipal(false);
        endereco.setPessoa(pessoa);

        enderecoRepository.save(endereco);
        return objectMapper.convertValue(endereco,EnderecoDTO.class);
    }

    public List<EnderecoDTO> listAllEnderecos(Integer idPessoa) throws RegraDeNegocioException {
        pessoaService.findByID(idPessoa);
        return enderecoRepository.findEnderecoEntityByIdPessoa(idPessoa)
                .stream()
                .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity, EnderecoDTO.class))
                .toList();
    }

    public EnderecoEntity findById(Integer id) throws RegraDeNegocioException {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));
    }

    public EnderecoDTO setEnderecoPrincipal(Integer idPessoa, Integer idEndereco) throws RegraDeNegocioException {
        pessoaService.findByID(idPessoa);
        EnderecoEntity enderecoEntity = findById(idEndereco);
        enderecoEntity.setPrincipal(true);

        enderecoRepository.save(enderecoEntity);

        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
    }

}
