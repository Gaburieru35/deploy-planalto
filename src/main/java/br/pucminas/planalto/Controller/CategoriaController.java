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

import br.pucminas.planalto.Model.Categoria;
import br.pucminas.planalto.Services.CategoriaService;

@RestController
@RequestMapping("/Categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping()
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> c = this.categoriaService.getAll();
        return ResponseEntity.ok().body(c);
    }

    @PostMapping()
    public ResponseEntity<Void> create(
            @RequestBody Categoria c) {
        this.categoriaService.create(c);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(c.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Categoria c) {
        this.categoriaService.update(c);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        Categoria c = this.categoriaService.findById(id);
        return ResponseEntity.ok().body(c);
    }
}
