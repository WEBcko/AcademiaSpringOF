package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long> {
    @Query("from Treino where codigoOrdem = :codigoOrdem")
    public List<Treino> findCodigo(@Param("codigoOrdem") final String codigoOrdem);
}
