package br.com.webcko.academia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "treinos_exercicios", schema = "public")
public class TreinoExercicio extends AbstractEntity {

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "id_treino", nullable = false)
    private Treino idTreino;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "id_exercicio", nullable = false)
    private Exercicio idExercicio;

    @Getter
    @Setter
    @Column(name = "dificuldade")
    private BigDecimal dificuldade;

    @Getter
    @Setter
    @Column(name="peso")
    private BigDecimal peso;

    @Getter
    @Setter
    @Column(name = "series")
    private BigDecimal series;

}
