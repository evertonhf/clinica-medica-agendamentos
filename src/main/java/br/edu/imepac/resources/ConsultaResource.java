package br.edu.imepac.resources;

import br.edu.imepac.dtos.Consulta.ConsultaCreateDTO;
import br.edu.imepac.dtos.Consulta.ConsultaDTO;
import br.edu.imepac.repositories.ConsultaRepository;
import br.edu.imepac.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaResource {

    @Autowired
    private ConsultaService consultaService;

    private ConsultaRepository consultaRepository;

    @PostMapping
    public ResponseEntity<ConsultaDTO> createConsulta(@RequestBody ConsultaCreateDTO consultaCreateDTO) {
        try {
            ConsultaDTO createdConsulta = consultaService.save(consultaCreateDTO);
            return new ResponseEntity<>(createdConsulta, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log da exceção, se necessário
            // logger.error("Erro ao criar consulta", e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna erro genérico
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> getConsulta(@PathVariable Long id) {
        try {
            ConsultaDTO consultaDTO = consultaService.findById(id);
            if (consultaDTO != null) {
                return new ResponseEntity<>(consultaDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se não encontrar
            }
        } catch (Exception e) {
            // Log da exceção, se necessário
            // logger.error("Erro ao buscar consulta com ID " + id, e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna erro genérico
        }
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> getAllConsultas() {
        try {
            List<ConsultaDTO> consultas = consultaService.findAll();
            return new ResponseEntity<>(consultas, HttpStatus.OK); // Retorna 200 OK com a lista
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna erro genérico
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        try {
            consultaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 se a exclusão for bem-sucedida
        } catch (Exception e) {
            // Log da exceção, se necessário
            // logger.error("Erro ao excluir consulta com ID " + id, e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna erro genérico
        }
    }
}