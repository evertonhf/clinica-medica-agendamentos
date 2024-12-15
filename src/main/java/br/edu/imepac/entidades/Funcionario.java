package br.edu.imepac.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "funcionario")
@Entity
@EntityScan(basePackages = "br.edu.imepac.entidades")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String usuario;
    private String senha;
    private String nome;
    private int idade;
    private String sexo;
    private String cpf;
    private String rua;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String contato;
    private String email;
    private Date dataNascimento;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
    //private Especialidade especialidade;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> medico;

    @OneToMany(mappedBy = "atendente")
    private List<Consulta> atendente;
}