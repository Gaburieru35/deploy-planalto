package br.pucminas.planalto.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.pucminas.planalto.Model.Categoria;
import br.pucminas.planalto.Repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public Categoria create(Categoria c){
        c.setId(null);
        Categoria c1 = c;
        c = this.categoriaRepository.saveAndFlush(c1);
        c.getId();
        return c;
    }

    public Categoria update(Categoria c){
        Categoria newCategoria = findById(c.getId());
        newCategoria.setNome(c.getNome());

        return this.categoriaRepository.save(newCategoria);
    }

    public Categoria findById(Integer id){
        Optional<Categoria> c = this.categoriaRepository.findById(id);
        return c.orElseThrow(() -> new RuntimeException(
            "Categoria não encontrada"
        ));
    }

    public List<Categoria> getAll(){
        List<Categoria> c = this.categoriaRepository.findAll();
        return c;
    }
    
}
