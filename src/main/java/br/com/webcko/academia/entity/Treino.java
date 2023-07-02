package br.com.webcko.academia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "treinos", schema = "public")
public class Treino extends AbstractEntity {

    @Getter @Setter
    @Column(name = "codigo_ordem", nullable = false, length = 10)
    private String codigoOrdem;

}
