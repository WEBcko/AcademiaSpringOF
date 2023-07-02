package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.Exercicio;
import br.com.webcko.academia.entity.Treino;
import br.com.webcko.academia.entity.TreinoExercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreinoExercicioRepository extends JpaRepository<TreinoExercicio, Long> {

    @Query("from TreinoExercicio where id = :idTreino")
    public List<TreinoExercicio> findTreino(@Param("idTreino") final Treino id_treino);

    @Query("from TreinoExercicio where id = :idExercicio")
    public List<TreinoExercicio> findExercicio(@Param("idExercicio") final Exercicio id_exercicio);

    /**
    @Query("from TreinoExercicio where dificildade = :dificuldade")
    public List<TreinoExercicio> findDificuldade(@Param("dificuldade") final String dificuldade);

    @Query("from TreinoExercicio where peso = :peso")
    public List<TreinoExercicio> findPeso(@Param("peso") final Float peso);

    @Query("from TreinoExercicio where series = :series")
    public List<TreinoExercicio> findSeries(@Param("series") final Integer series);
    */
}
