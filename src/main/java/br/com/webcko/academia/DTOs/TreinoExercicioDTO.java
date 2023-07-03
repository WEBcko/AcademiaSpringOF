package br.com.webcko.academia.DTOs;

import br.com.webcko.academia.entity.Exercicio;
import br.com.webcko.academia.entity.Treino;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TreinoExercicioDTO {

    private Treino idTreino;

    private Exercicio idExercicio;

    private BigDecimal dificuldade;

    private BigDecimal peso;

    private BigDecimal series;
}
