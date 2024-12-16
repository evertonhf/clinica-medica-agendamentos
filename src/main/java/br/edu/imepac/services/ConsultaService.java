package br.edu.imepac.services;

import br.edu.imepac.dtos.consultas.ConsultaCreateDTO;
import br.edu.imepac.dtos.consultas.ConsultaDTO;
import br.edu.imepac.dtos.consultas.ConsultaUpdateDTO;
import br.edu.imepac.entidades.Consulta;
import br.edu.imepac.repositories.ConsultaRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ConsultaService(ConsultaRepository consultaRepository, ModelMapper modelMapper) {
        this.consultaRepository = consultaRepository;
        this.modelMapper = modelMapper;
    }

    public ConsultaDTO save(ConsultaCreateDTO consultaCreateDto) {
        Consulta consulta = modelMapper.map(consultaCreateDto, Consulta.class);
        consulta = consultaRepository.save(consulta);
        return modelMapper.map(consulta, ConsultaDTO.class);
    }

    public void delete(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada!"));
        consultaRepository.delete(consulta);
        log.info("Consulta deletada com sucesso!");
    }

    public ConsultaDTO update(Long id, ConsultaUpdateDTO consultaUpdateDto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada!"));
        modelMapper.map(consultaUpdateDto, consulta);
        consulta = consultaRepository.save(consulta);
        return modelMapper.map(consulta, ConsultaDTO.class);
    }

    public ConsultaDTO findById(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada!"));
        return modelMapper.map(consulta, ConsultaDTO.class);
    }

    public List<ConsultaDTO> findAll() {
        List<Consulta> consultas = consultaRepository.findAll();
        return modelMapper.map(consultas, List.class);
    }
}