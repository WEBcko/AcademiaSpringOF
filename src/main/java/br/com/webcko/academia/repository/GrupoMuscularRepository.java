package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.GrupoMuscular;
import br.com.webcko.academia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoMuscularRepository extends JpaRepository<GrupoMuscular, Long> {

    @Query("from Usuario where ativo = true")
    public List<Usuario> findByAtivos ();

}
