package br.com.webcko.academia.DTOs;

import br.com.webcko.academia.entity.GrupoMuscular;
import lombok.Data;

@Data
public class ExercicioDTO{

    private GrupoMuscular idGrupoMuscular;

    private String nome;

}
