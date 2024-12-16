package br.edu.imepac.resources;

import br.edu.imepac.dtos.consultas.ConsultaCreateDTO;
import br.edu.imepac.dtos.consultas.ConsultaDTO;
import br.edu.imepac.services.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequestMapping("/api/consultas")
public class ConsultaResource {

    private final ConsultaService consultaService;

    @Autowired
    public ConsultaResource(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @Operation(summary = "Salvar uma nova consulta", description = "Cria uma nova consulta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Consulta criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    @ResponseStatus(CREATED)
    public ConsultaDTO createConsulta(@RequestBody @Parameter(description = "Dados da consulta a ser criada") ConsultaCreateDTO consultaCreateDTO) {
        log.info("Tentando salvar uma nova consulta.");
        return consultaService.save(consultaCreateDTO);
    }

    @Operation(summary = "Buscar consulta por ID", description = "Obtém uma consulta específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta encontrada"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ConsultaDTO getConsulta(@Parameter(description = "ID da consulta", required = true) @PathVariable Long id) {
        log.info("Buscando consulta com ID: {}", id);
        return consultaService.findById(id);
    }

    @Operation(summary = "Listar todas as consultas", description = "Obtém todas as consultas disponíveis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de consultas retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    @ResponseStatus(OK)
    public List<ConsultaDTO> getAllConsultas() {
        log.info("Listando todas as consultas.");
        return consultaService.findAll();
    }

    @Operation(summary = "Remover uma consulta por ID", description = "Deleta uma consulta com base no seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Consulta removida com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteConsulta(@Parameter(description = "ID da consulta", required = true) @PathVariable Long id) {
        log.info("Removendo consulta com ID: {}", id);
        consultaService.delete(id);
    }
}