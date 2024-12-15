package br.edu.imepac.services;

import br.edu.imepac.dtos.Consulta.ConsultaCreateDTO;
import br.edu.imepac.dtos.Consulta.ConsultaDTO;
import br.edu.imepac.entidades.Consulta;
import br.edu.imepac.entidades.Funcionario;
import br.edu.imepac.entidades.Paciente;
import br.edu.imepac.repositories.ConsultaRepository;
import br.edu.imepac.repositories.FuncionarioRepository;
import br.edu.imepac.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public ConsultaDTO save(ConsultaCreateDTO consultaCreate) {
        try {
            // Buscar o Paciente pelo ID
            Optional<Paciente> optionalPaciente = pacienteRepository.findById(consultaCreate.getPaciente_id());
            if (!optionalPaciente.isPresent()) {
                throw new IllegalArgumentException("Paciente não encontrado");
            }
            Paciente paciente = optionalPaciente.get();

            // Buscar o Funcionario pelo ID
            if (consultaCreate.getMedico_id() != 0 && consultaCreate.getAtendente_id() != 0 && consultaCreate.getPaciente_id() != 0) {
                Optional<Funcionario> optionalMedico = funcionarioRepository.findById(consultaCreate.getMedico_id());
                Optional<Funcionario> optionalAtendente = funcionarioRepository.findById(consultaCreate.getAtendente_id());

                if (!optionalMedico.isPresent()) {
                    throw new IllegalArgumentException("Médico não encontrado");
                }

                if (!optionalAtendente.isPresent()) {
                    throw new IllegalArgumentException("Atendente não encontrado");
                }

                Funcionario medico = optionalMedico.get();
                Funcionario atendente = optionalAtendente.get();

                // Criar a consulta
                Consulta consulta = new Consulta();
                consulta.setPaciente(paciente);
                consulta.setMedico(medico);
                consulta.setAtendente(atendente);
                consulta.setDataHorario(consultaCreate.getDataHorario());
                consulta.setSintomas(consultaCreate.getSintomas());
                consulta.setERetorno(consultaCreate.isERetorno());
                consulta.setEstaAtiva(consultaCreate.isEstaAtiva());
                consulta.setConvenio(consultaCreate.getConvenio());
                consulta.setProntuario(consultaCreate.getProntuario());

                // Salvar a consulta

                consulta = consultaRepository.save(consulta);

                // Converter para DTO e retornar
                return convertToDTO(consulta);
            } else {
                throw new IllegalArgumentException("ID do Atendente não informado");
            }
        } catch (IllegalArgumentException e) {
            throw e;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar consulta", e);
        }
    }

    public ConsultaDTO update(Long id, ConsultaCreateDTO consultaCreateDTO) {
        try {
            // Verifica se a consulta existe
            Optional<Consulta> optionalConsulta = consultaRepository.findById(id);
            if (!optionalConsulta.isPresent()) {
                throw new IllegalArgumentException("Consulta não encontrada");
            }

            Consulta consulta = optionalConsulta.get();

            // Atualiza o paciente apenas se o ID for fornecido
            if (consultaCreateDTO.getPaciente_id() != 0) {
                Optional<Paciente> optionalPaciente = pacienteRepository.findById(consultaCreateDTO.getPaciente_id());
                if (!optionalPaciente.isPresent()) {
                    throw new IllegalArgumentException("Paciente não encontrado");
                }
                consulta.setPaciente(optionalPaciente.get());
            }

            // Atualiza o médico apenas se o ID for fornecido
            if (consultaCreateDTO.getMedico_id() != 0) {
                Optional<Funcionario> optionalMedico = funcionarioRepository.findById(consultaCreateDTO.getMedico_id());
                if (!optionalMedico.isPresent()) {
                    throw new IllegalArgumentException("Médico não encontrado");
                }
                consulta.setMedico(optionalMedico.get());
            }

            // Atualiza o atendente apenas se o ID for fornecido
            if (consultaCreateDTO.getAtendente_id() != 0) {
                Optional<Funcionario> optionalAtendente = funcionarioRepository.findById(consultaCreateDTO.getAtendente_id());
                if (!optionalAtendente.isPresent()) {
                    throw new IllegalArgumentException("Atendente não encontrado");
                }
                consulta.setAtendente(optionalAtendente.get());
            }

            // Atualiza os campos restantes, se fornecidos
            if (consultaCreateDTO.getDataHorario() != null) {
                consulta.setDataHorario(consultaCreateDTO.getDataHorario());
            }

            if (consultaCreateDTO.getSintomas() != null) {
                consulta.setSintomas(consultaCreateDTO.getSintomas());
            }

            // Atualiza os campos booleanos
            consulta.setERetorno(consultaCreateDTO.isERetorno());
            consulta.setEstaAtiva(consultaCreateDTO.isEstaAtiva());

            // Atualiza o convênio e o prontuário apenas se fornecidos
            if (consultaCreateDTO.getConvenio() != null) {
                consulta.setConvenio(consultaCreateDTO.getConvenio());
            }

            if (consultaCreateDTO.getProntuario() != null) {
                consulta.setProntuario(consultaCreateDTO.getProntuario());
            }

            // Salva a consulta atualizada
            consulta = consultaRepository.save(consulta);

            // Retorna o DTO da consulta atualizada
            return convertToDTO(consulta);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar consulta", e);
        }
    }

    // Buscar uma consulta
    public ConsultaDTO findById(Long id) {
        try {
            Optional<Consulta> optionalConsultaDTO = consultaRepository.findById(id);
            if (optionalConsultaDTO.isPresent()) {
                return convertToDTO(optionalConsultaDTO.get());
            } else {
                return null; // Ou lançar uma exceção personalizada como NoSuchElementException
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar consulta", e);
        }
    }

    // Listar as consultas
    public List<ConsultaDTO> findAll() {
        try {
            List<Consulta> consultas = consultaRepository.findAll();
            return consultas.stream().map(this::convertToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            // Loga a exceção, se necessário
            // logger.error("Erro ao listar consultas", e);
            throw new RuntimeException("Erro ao listar consultas", e);
        }
    }

    public void delete(Long id) {
        try {
            consultaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar consulta", e);
        }
    }

    private ConsultaDTO convertToDTO(Consulta consulta) {
        ConsultaDTO consultaDTO = new ConsultaDTO();

        consultaDTO.setDataHorario(consulta.getDataHorario());
        consultaDTO.setSintomas(consulta.getSintomas());
        consultaDTO.setERetorno(consulta.isERetorno());
        consultaDTO.setEstaAtiva(consulta.isEstaAtiva());
        return consultaDTO;
    }
}