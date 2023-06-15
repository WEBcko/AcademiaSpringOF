package br.com.webcko.academia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "usuarios", schema = "public")
public class Usuario extends AbstractEntity{

    @Getter
    @Setter
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Getter @Setter
    @Column(name = "peso", nullable = false)
    private BigDecimal peso;

    @Getter @Setter
    @Column(name = "altura", nullable = false)
    private BigDecimal altura;

    @Getter @Setter
    @Column(name = "cep", nullable = false, length = 10)
    private String cep;

    @Getter @Setter
    @Column(name = "numero_casa", nullable = false)
    private int numeroCasa;

    @Getter @Setter
    @Column(name = "data_nascimento", nullable = false, columnDefinition = "DATE")
    private LocalDate dataNascimento;

    @Getter @Setter
    @Column(name = "telefone", nullable = false, length = 30)
    private String telefone;

    @Getter @Setter
    @Column(name = "cpf", nullable = false, unique = true, length = 16)
    private String cpf;

    @Getter @Setter
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Getter @Setter
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UsuarioRole role;

}