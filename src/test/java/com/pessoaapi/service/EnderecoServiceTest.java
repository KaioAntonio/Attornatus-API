package com.pessoaapi.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pessoaapi.dto.endereco.EnderecoCreateDTO;
import com.pessoaapi.dto.endereco.EnderecoDTO;
import com.pessoaapi.dto.pessoa.PessoaDTO;
import com.pessoaapi.entity.EnderecoEntity;
import com.pessoaapi.entity.PessoaEntity;
import com.pessoaapi.exceptions.RegraDeNegocioException;
import com.pessoaapi.factory.EnderecoFactory;
import com.pessoaapi.factory.PessoaFactory;
import com.pessoaapi.repository.EnderecoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private PessoaService pessoaService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ReflectionTestUtils.setField(enderecoService, "objectMapper", objectMapper);
    }

    @Test
    public void deveTestarCreateComSucesso() throws RegraDeNegocioException {
        EnderecoCreateDTO enderecoCreateDTO = EnderecoFactory.getEnderecoCreateDTO();
        PessoaDTO pessoa = PessoaFactory.getPessoaDTO();
        EnderecoEntity enderecoEntity = EnderecoFactory.getEnderecoEntity();
        when(enderecoRepository.save(any())).thenReturn(enderecoEntity);
        when(pessoaService.findByID(any())).thenReturn(pessoa);

        EnderecoDTO enderecoDTO = enderecoService.create(enderecoCreateDTO);

        assertNotNull(enderecoDTO);
    }

    @Test
    public void deveTestarListAllEnderecosComSucesso() throws RegraDeNegocioException {
        PessoaDTO pessoa = PessoaFactory.getPessoaDTO();
        Integer id = 1;
        EnderecoEntity enderecoEntity = EnderecoFactory.getEnderecoEntity();
        List<EnderecoEntity> enderecoEntities = new ArrayList<>();
        enderecoEntities.add(enderecoEntity);
        when(pessoaService.findByID(anyInt())).thenReturn(pessoa);
        when(enderecoRepository.findEnderecoEntityByIdPessoa(anyInt())).thenReturn(enderecoEntities);

        List<EnderecoDTO> enderecos = enderecoService.listAllEnderecos(id);

        assertNotNull(enderecos);
    }

    @Test
    public void deveTestarFindByIdComSucesso() throws RegraDeNegocioException {
        Integer id = 1;
        EnderecoEntity enderecoEntity = EnderecoFactory.getEnderecoEntity();
        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.of(enderecoEntity));

        EnderecoEntity endereco = enderecoService.findById(id);

        assertNotNull(endereco);
        assertEquals(1, endereco.getId());

    }

    @Test(expected = RegraDeNegocioException.class)
    public void deveTestarFindByIdComErro() throws RegraDeNegocioException {
        Integer id = 10;
        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.empty());

        EnderecoEntity endereco = enderecoService.findById(id);
    }

    @Test
    public void deveTestarEnderecoPrincipalComSucesso() throws RegraDeNegocioException {
        Integer idPessoa = 1;
        Integer idEndereco = 1;
        EnderecoEntity enderecoEntity = EnderecoFactory.getEnderecoEntity();
        PessoaDTO pessoa = PessoaFactory.getPessoaDTO();
        when(pessoaService.findByID(anyInt())).thenReturn(pessoa);
        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.of(enderecoEntity));
        when(enderecoRepository.save(any())).thenReturn(enderecoEntity);

        EnderecoDTO enderecoDTO = enderecoService.setEnderecoPrincipal(idPessoa, idEndereco);

        assertNotNull(enderecoDTO);
        assertEquals(true, enderecoDTO.getPrincipal());

    }

}
