package br.pucminas.planalto.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.pucminas.planalto.Model.Bebida;
import br.pucminas.planalto.Services.BebidaService;



@RestController
@RequestMapping("/Bebida")
@CrossOrigin(origins = "*")
public class BebidaController {

    @Autowired
    private BebidaService bebidaService;
    
    @GetMapping()
    public ResponseEntity<List<Bebida>>findAll() {
        List<Bebida> b = this.bebidaService.getAll();
        return ResponseEntity.ok().body(b);
    }

    @PostMapping()
    public ResponseEntity<Bebida> create(@RequestBody Bebida b) {
        this.bebidaService.create(b);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(b.getId()).toUri();
        
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bebida> update(@PathVariable Integer id, @RequestBody Bebida b) {
        this.bebidaService.update(b);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bebida> findById(@PathVariable Integer id){
        Bebida b = this.bebidaService.findById(id);
        return ResponseEntity.ok().body(b);
    }    
    
    
}
