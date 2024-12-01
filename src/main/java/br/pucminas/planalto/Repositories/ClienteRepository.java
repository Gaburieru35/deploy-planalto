package br.pucminas.planalto.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.pucminas.planalto.Model.*;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    Optional<Cliente> findByUsuario_Id(Integer id);

    @Query(value = "SELECT * FROM cliente WHERE idusuario = :id", nativeQuery = true)
    Optional<Cliente> findByIdUsuario(@Param("id") Integer id);
}
