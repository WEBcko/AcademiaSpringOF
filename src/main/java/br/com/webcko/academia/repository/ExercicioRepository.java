package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.Exercicio;
import br.com.webcko.academia.entity.GrupoMuscular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {

    @Query("from Exercicio where GrupoMuscular = :grupo")
    public List<Exercicio> findExerciciosByGrupo(@Param("grupo") GrupoMuscular grupo);

    @Query("from Exercicio where id = :idExercicio")
    public List<Exercicio> findExercicios(@Param("idExercicio") final Long id);

    @Query("from Exercicio where id = :idTreino")
    public List<Exercicio> findTreinos (@Param("idTreino")final Long id);

}
