package com.pessoaapi.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pessoaapi.dto.pessoa.PessoaCreateDTO;
import com.pessoaapi.dto.pessoa.PessoaDTO;
import com.pessoaapi.entity.PessoaEntity;
import com.pessoaapi.exceptions.RegraDeNegocioException;
import com.pessoaapi.factory.PessoaFactory;
import com.pessoaapi.repository.PessoaRepository;
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
public class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ReflectionTestUtils.setField(pessoaService, "objectMapper", objectMapper);
    }

    @Test
    public void deveTestarCreateComSucesso(){
        PessoaCreateDTO pessoaCreateDTO = PessoaFactory.getPessoaCreateDTO();
        PessoaEntity pessoa = PessoaFactory.getPessoaEntity();
        when(pessoaRepository.save(any())).thenReturn(pessoa);

        PessoaDTO pessoaDTO = pessoaService.create(pessoaCreateDTO);

        assertNotNull(pessoaDTO);
    }

    @Test
    public void deveTestarListAllComSucesso(){
        List<PessoaEntity> pessoas = new ArrayList<>();
        pessoas.add(PessoaFactory.getPessoaEntity());
        when(pessoaRepository.findAll()).thenReturn(pessoas);

        List<PessoaDTO> pessoaDTOS = pessoaService.listAll();

        assertNotNull(pessoaDTOS);
        assertEquals(1, pessoaDTOS.size());
    }

    @Test
    public void deveTestarFindByIdComSucesso() throws RegraDeNegocioException {
        Integer id = 1;
        PessoaEntity pessoa = PessoaFactory.getPessoaEntity();
        when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(pessoa));

        PessoaDTO pessoaDTO = pessoaService.findByID(id);

        assertNotNull(pessoaDTO);
        assertEquals(1, pessoaDTO.getId());
    }

    @Test(expected = RegraDeNegocioException.class)
    public void deveTestarFindByIdComErro() throws RegraDeNegocioException {
        Integer id = 10;
        when(pessoaRepository.findById(anyInt())).thenReturn(Optional.empty());

        PessoaDTO pessoa = pessoaService.findByID(id);
    }

    @Test
    public void deveTestarEditComSucesso() throws RegraDeNegocioException {
        Integer id = 1;
        PessoaCreateDTO pessoaCreateDTO = PessoaFactory.getPessoaCreateDTO();
        PessoaEntity pessoa = PessoaFactory.getPessoaEntity();
        when(pessoaRepository.save(any())).thenReturn(pessoa);
        when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(pessoa));

        PessoaDTO pessoaDTO = pessoaService.edit(id, pessoaCreateDTO);

        assertNotNull(pessoaDTO);
    }
}
