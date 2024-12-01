package br.pucminas.planalto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucminas.planalto.Model.Bebida;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Integer> {
    
}
