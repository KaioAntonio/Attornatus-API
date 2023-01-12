package com.pessoaapi.controller;

import com.pessoaapi.dto.endereco.EnderecoCreateDTO;
import com.pessoaapi.dto.endereco.EnderecoDTO;
import com.pessoaapi.entity.EnderecoEntity;
import com.pessoaapi.exceptions.RegraDeNegocioException;
import com.pessoaapi.service.EnderecoService;
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
@RequestMapping("/endereco")
@Validated
@RequiredArgsConstructor
@Slf4j
public class EnderecoController {
    private final EnderecoService enderecoService;

    @Operation(summary = "Criar Endereco", description = "Cria um endereco no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Endereco Criado com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/{idPessoa}")
    ResponseEntity<EnderecoDTO> salvar(@Valid @RequestBody EnderecoCreateDTO pessoa) throws RegraDeNegocioException {
        return new ResponseEntity<>(enderecoService.create(pessoa), HttpStatus.OK);
    }

    @Operation(summary = "Listar Endereco por Id da pessoa", description = "Listar Endereco por Id da pessoa do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Listagem com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idPessoa}")
    ResponseEntity<List<EnderecoDTO>> listarTodosEnderecosPorPessoa(@PathVariable(name = "idPessoa") Integer idPessoa) throws RegraDeNegocioException {
        return new ResponseEntity<>(enderecoService.listAllEnderecos(idPessoa), HttpStatus.OK);
    }

    @Operation(summary = "Listar Endereco por Id da pessoa", description = "Listar Endereco por Id da pessoa do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Listagem com sucesso!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idPessoa}/{idEndereco}")
    ResponseEntity<EnderecoDTO> informarEnderecoPrincipal(@PathVariable(name = "idPessoa") Integer idPessoa,
                                                                    @PathVariable(name = "idEndereco") Integer idEndereco)
                                                                    throws RegraDeNegocioException {
        return new ResponseEntity<>(enderecoService.setEnderecoPrincipal(idPessoa, idEndereco), HttpStatus.OK);
    }
}
