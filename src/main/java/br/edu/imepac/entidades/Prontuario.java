package br.edu.imepac.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prontuarios")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "receituario", nullable = false)
    private String receituario;
    @Column(name = "exames")
    private String exames;
    @Column(name = "observacoes")
    private String observacoes;
}
