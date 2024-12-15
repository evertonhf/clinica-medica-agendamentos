package br.edu.imepac.dtos.Consulta;

import br.edu.imepac.entidades.Funcionario;
import br.edu.imepac.entidades.Paciente;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ConsultaDTO{

    private LocalDateTime dataHorario;
    private String sintomas;
    private boolean eRetorno;
    private boolean estaAtiva;

    private long medico;

    private long atendente;

    private long paciente;

    private String prontuario;

    private String convenio;
}
