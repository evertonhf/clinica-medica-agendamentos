package br.edu.imepac;

import br.edu.imepac.dtos.consultas.ConsultaDTO;
import br.edu.imepac.dtos.convenios.ConvenioDTO;
import br.edu.imepac.dtos.funcionarios.FuncionarioDTO;
import br.edu.imepac.dtos.pacientes.PacienteDTO;
import br.edu.imepac.entidades.Consulta;
import br.edu.imepac.entidades.Funcionario;
import br.edu.imepac.entidades.Paciente;
import br.edu.imepac.entidades.Convenio;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClinicaMedicaAgendamentosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicaMedicaAgendamentosApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<Funcionario, FuncionarioDTO> funcionarioConverter = context -> modelMapper.map(context.getSource(), FuncionarioDTO.class);
        Converter<Paciente, PacienteDTO> pacienteConverter = context -> modelMapper.map(context.getSource(), PacienteDTO.class);
        Converter<Convenio, ConvenioDTO> convenioConverter = context -> modelMapper.map(context.getSource(), ConvenioDTO.class);

        modelMapper.addMappings(new PropertyMap<Consulta, ConsultaDTO>() {
            @Override
            protected void configure() {
                using(funcionarioConverter).map(source.getMedico(), destination.getMedicoDTO());
                using(funcionarioConverter).map(source.getAtendente(), destination.getAtendenteDTO());
                using(pacienteConverter).map(source.getPaciente(), destination.getPacienteDTO());
                using(convenioConverter).map(source.getConvenio(), destination.getConvenioDTO());
            }
        });

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }
}