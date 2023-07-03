package br.com.webcko.academia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "grupo_muscular", schema = "public")
public class GrupoMuscular extends AbstractEntity {

    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Override
    public String toString() {
        return "GrupoMuscular{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
