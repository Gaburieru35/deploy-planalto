package br.pucminas.planalto.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.pucminas.planalto.Model.*;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT * FROM usuario where emailusuario = :emailusuario", nativeQuery = true)
    Optional<Usuario> findByEmail(@Param("emailusuario") String email);
}