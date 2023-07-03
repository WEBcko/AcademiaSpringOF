package br.com.webcko.academia.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {

        @Id
        @Getter
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false)
        private Long id;

        @Getter @Setter
        @Column(name = "dt_cadastrado", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
        protected LocalDateTime dataCadastrado;

        @Getter @Setter
        @Column(name = "dt_atualizado", nullable = true, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
        protected LocalDateTime dataAtualizado;

        @Getter @Setter
        @Column(name = "ativo", nullable = false)
        protected boolean ativo;

        @PrePersist
        private void prePersiste(){
                this.dataCadastrado = LocalDateTime.now();
                this.ativo=true;
        }

        @PreUpdate
        private void preUpdate(){
                this.dataAtualizado = LocalDateTime.now();
        }

        @Override
        public String toString() {
                return "AbstractEntity{" +
                        "id=" + id +
                        '}';
        }
}
