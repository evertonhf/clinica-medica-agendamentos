package br.edu.imepac.entidades;

import lombok.Data;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consulta")

public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private LocalDateTime dataHorario;
    private String sintomas;
    private boolean eRetorno;
    private boolean estaAtiva;

    @OneToOne(mappedBy = "medico",cascade = CascadeType.REMOVE)
    private String medico;

    @OneToMany(mappedBy = "atendente", cascade = CascadeType.REMOVE)

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.REMOVE)
    private String paciente;

    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.REMOVE)
    private String prontuario;

    @OneToMany(mappedBy = "convenio", cascade = CascadeType.REMOVE)
    private String convenio;

}
