package br.edu.imepac.dtos.especialidades;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EspecialidadeDTO {

    private int id;
    private String nome;
    private String descricao;
}
