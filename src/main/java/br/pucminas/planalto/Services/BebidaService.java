package br.pucminas.planalto.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  br.pucminas.planalto.Model.Bebida;
import  br.pucminas.planalto.Repositories.BebidaRepository;

@Service
public class BebidaService {

    @Autowired
    private BebidaRepository bebidaRepository;

    @Transactional
    public Bebida create(Bebida b){
        b.setId(null);
        Bebida b1 = b;
        b = this.bebidaRepository.saveAndFlush(b1);
        b.getId();
        return b;
    }

    public Bebida update(Bebida b){
        Bebida newBebida = findById(b.getId());
        newBebida.setNome(b.getNome());
        newBebida.setPreco(b.getPreco());
        newBebida.setDescricao(b.getDescricao());
        newBebida.setQuantidade(b.getQuantidade());
        newBebida.setCategoria(b.getCategoria());
        newBebida.setFoto(b.getFoto());

        return this.bebidaRepository.save(newBebida);
    }

    public Bebida findById(Integer id){
        Optional<Bebida> b = this.bebidaRepository.findById(id);
        return b.orElseThrow(() -> new RuntimeException(
            "Bebida não encontrada"
        ));
    }

    public List<Bebida> getAll(){
        List<Bebida> b = this.bebidaRepository.findAll();
        return b;
    }
    
}
