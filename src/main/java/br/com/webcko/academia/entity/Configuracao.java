package br.com.webcko.academia.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "configuracoes", schema = "public")
public class Configuracao extends AbstractEntity{

    @Getter @Setter
    @Column(name = "hora_abrir", nullable = false)
    private LocalTime horaAbrir;

    @Getter @Setter
    @Column(name = "hora_fechar", nullable = false)
    private LocalTime horaFechar;

    @Getter @Setter
    @Column(name = "ocupacao", nullable = false)
    private Integer ocupacao;
}
