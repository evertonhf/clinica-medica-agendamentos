package br.edu.imepac.dtos.consultas;

import br.edu.imepac.dtos.convenios.ConvenioDTO;
import br.edu.imepac.dtos.funcionarios.FuncionarioDTO;
import br.edu.imepac.dtos.pacientes.PacienteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ConsultaCreateDTO {

    private LocalDateTime dataHorario;
    private String sintomas;
    private boolean eRetorno;
    private boolean estaAtiva;

    private FuncionarioDTO medicoDTO;

    private FuncionarioDTO atendenteDTO;

    private PacienteDTO pacienteDTO;

    private ConvenioDTO convenioDTO;
}
