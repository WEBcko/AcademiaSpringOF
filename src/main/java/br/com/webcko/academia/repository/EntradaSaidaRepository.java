package br.com.webcko.academia.repository;

import br.com.webcko.academia.entity.EntradaSaida;
import br.com.webcko.academia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradaSaidaRepository extends JpaRepository<EntradaSaida, Long> {

    @Query("from EntradaSaida where cliente = :cliente")
    public List<EntradaSaida> findEntradaSaidaByIdCliente(@Param("cliente") Usuario cliente);

    @Query("from EntradaSaida where personal = :personal")
    public List<EntradaSaida> findEntradaSaidaByIdPersonal(@Param("personal") Usuario personal);
}
