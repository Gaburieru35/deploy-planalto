package br.pucminas.planalto.Controller;

import java.net.URI;
import java.util.ArrayList;
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
import br.pucminas.planalto.Model.PedidoBebida;
import br.pucminas.planalto.Model.Listagens.ListagemPedidoModel;
import br.pucminas.planalto.Services.PedidoBebidaService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/PedidoBebida")
@CrossOrigin(origins = "*")
public class PedidoBebidaController {
    
    @Autowired
    private PedidoBebidaService pedidoBebidaService;

    @GetMapping()
    public ResponseEntity<List<PedidoBebida>>findAll() {
        List<PedidoBebida> p = this.pedidoBebidaService.getAll();
        List<PedidoBebida> newList = new ArrayList<PedidoBebida>();
        for (PedidoBebida pedidoBebida : p) {
            newList.add(pedidoBebida);
        }
        return ResponseEntity.ok().body(newList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoBebida> findById(Integer id){
        PedidoBebida p = this.pedidoBebidaService.findById(id);
        return ResponseEntity.ok().body(p);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/Pedido/{idUsuario}")
    public ResponseEntity<List<ListagemPedidoModel>> getPedidosPorUsuarioEId(
            @PathVariable Long idUsuario) 
    {
        List<ListagemPedidoModel> pedidos = pedidoBebidaService.buscarPedidosPorUsuarioEId(idUsuario);
        return ResponseEntity.ok().body(pedidos);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/Pedido")
    public ResponseEntity<List<ListagemPedidoModel>> getTodosPedidos() 
    {
        List<ListagemPedidoModel> pedidos = pedidoBebidaService.buscarTodosPedidos();
        return ResponseEntity.ok().body(pedidos);
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody PedidoBebida p) {
        this.pedidoBebidaService.create(p);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoBebida> update(@RequestBody PedidoBebida p) {
        this.pedidoBebidaService.update(p);
        return ResponseEntity.noContent().build();
    }
}
