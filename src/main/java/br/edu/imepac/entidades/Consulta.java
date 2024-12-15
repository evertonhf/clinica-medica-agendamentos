package br.edu.imepac.entidades;

import lombok.Data;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "consulta")
@Entity
@EntityScan(basePackages = "br.edu.imepac.entidades")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  
    private long id;

    private LocalDateTime dataHorario;
    private String sintomas;
    private boolean eRetorno;
    private boolean estaAtiva;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medico_id")
    private Funcionario medico;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "atendente_id")
    private Funcionario atendente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    //@ManyToOne(mappedBy = "prontuario", cascade = CascadeType.REMOVE)
    private String prontuario;

    //@OneToOne(mappedBy = "convenio", cascade = CascadeType.REMOVE)
    private String convenio;
}
