package br.pucminas.planalto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pucminas.planalto.Model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    
} 
