package br.edu.imepac.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "perfil")
@Entity
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private boolean cadastrarFuncionario;
    private boolean lerFuncionario;
    private boolean atualizarFuncionario;
    private boolean deletarFuncionario;
    private boolean listarFuncionario;
    private boolean cadastrarPaciente;
    private boolean lerPaciente;
    private boolean atualizarPaciente;
    private boolean deletarPaciente;
    private boolean listarPaciente;
    private boolean cadastrarFormulario;
    private boolean lerFormulario;
    private boolean atualizarFormulario;
    private boolean deletarFormulario;
    private boolean listarFormulario;
    private boolean cadastrarFspecialidade;
    private boolean lerEspecialidade;
    private boolean atualizarEspecialidade;
    private boolean deletarEspecialidade;
    private boolean listarEspecialidade;
    private boolean cadastrarConvenio;
    private boolean lerConvenio;
    private boolean atualizarConvenio;
    private boolean deletarConvenio;
    private boolean listarConvenio;

    @OneToMany(mappedBy = "perfil")
    private List<Funcionario> funcionario;
}
