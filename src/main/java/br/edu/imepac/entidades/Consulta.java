package br.edu.imepac.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "consultas")
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime dataHorario;
    private String sintomas;
    private boolean eRetorno;
    private boolean estaAtiva;


    @ManyToOne(fetch = FetchType.EAGER)
    private Funcionario medico;

    @ManyToOne(fetch = FetchType.EAGER)
    private Funcionario atendente;

    @ManyToOne(fetch = FetchType.EAGER)
    private Paciente paciente;

    @OneToOne(fetch = FetchType.EAGER)
    private Prontuario prontuario;

    @ManyToOne(fetch = FetchType.EAGER)
    private Convenio convenio;
}
