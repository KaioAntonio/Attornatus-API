package com.pessoaapi.controller;

import com.pessoaapi.dto.pessoa.PessoaCreateDTO;
import com.pessoaapi.dto.pessoa.PessoaDTO;
import com.pessoaapi.exceptions.RegraDeNegocioException;
import com.pessoaapi.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
@Validated
@RequiredArgsConstructor
@Slf4j
public class PessoaController {

    private final PessoaService pessoaService;

    @Operation(summary = "Criar Pessoa", description = "Cria uma pessoa no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Pessoa Criado com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<PessoaDTO> salvar(@Valid @RequestBody PessoaCreateDTO pessoa) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.create(pessoa), HttpStatus.OK);
    }

    @Operation(summary = "Listar Pessoas", description = "Listar todas pessoas do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Listagem com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<PessoaDTO>> listarTodos(){
        return new ResponseEntity<>(pessoaService.listAll(), HttpStatus.OK);
    }

    @Operation(summary = "Listar uma Pessoa por Id", description = "Listar uma pessoa do banco de dados por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Listagem com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<PessoaDTO> listarPessoaPorId(@PathVariable(name = "id") Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.findByID(id), HttpStatus.OK);
    }

    @Operation(summary = "Editar uma Pessoa por Id", description = "Editar uma pessoa do banco de dados por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Editado(a) com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<PessoaDTO> editar(@PathVariable(name = "id") Integer id,
                                     @RequestBody @Valid PessoaCreateDTO pessoa) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.edit(id, pessoa), HttpStatus.OK);
    }

}
