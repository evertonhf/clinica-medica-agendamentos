package br.edu.imepac.dtos.funcionarios;

import br.edu.imepac.dtos.especialidades.EspecialidadeDTO;
import br.edu.imepac.dtos.perfis.PerfilDTO;
import br.edu.imepac.entidades.EnumTipoFuncionario;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FuncionarioDTO {

    private int id;
    private String usuario;
    private int senha;
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

    private EnumTipoFuncionario tipoFuncionario;

    private PerfilDTO perfilDTO;

    private EspecialidadeDTO especialidadeDTO;
}


