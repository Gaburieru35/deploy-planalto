package br.pucminas.planalto.Controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

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
import br.pucminas.planalto.Model.Cliente;
import br.pucminas.planalto.Model.Pedido;
// import br.pucminas.planalto.Model.PedidoBebida;
import br.pucminas.planalto.Services.PedidoBebidaService;
import br.pucminas.planalto.Services.PedidoService;
import br.pucminas.planalto.Services.UsuarioService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/Pedido")
@CrossOrigin(origins = "*")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired 
    private PedidoBebidaService pedidoBebidaService;

    @GetMapping()
    public ResponseEntity<List<Pedido>>findAll() {
        List<Pedido> p = this.pedidoService.getAll();
        return ResponseEntity.ok().body(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(Integer id){
        Pedido p = this.pedidoService.findById(id);
        return ResponseEntity.ok().body(p);
    }

    @PostMapping()
    public ResponseEntity<Pedido> create(@RequestBody Pedido p) {
        this.pedidoService.create(p);

        // List<Bebida> bebidas = p.getItens();

        // for (Bebida bebida : bebidas) {
        //     PedidoBebida pb = new PedidoBebida();
        //     pb.setBebida(bebida.getId());
        //     pb.setPedido(p.getId());
        //     this.pedidoBebidaService.create(pb.getPedido(), pb.getBebida(), pb.getQuantidade());
        // }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.ok().body(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Pedido p) {
        this.pedidoService.update(p);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Integer id, @PathVariable Integer status) {
        this.pedidoService.atualizarStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/Usuario/{id}")
    public ResponseEntity<List<Pedido>> findByIdCliente(@PathVariable Integer id){

        List<Pedido> p = this.pedidoService.findByIdCliente(id);
        return ResponseEntity.ok().body(p);
    }

    @GetMapping("/total-por-dia-semana-ultima-semana")
    public ResponseEntity<Map<String, Double>> getTotalPedidosPorDiaSemanaUltimaSemana() {
        Map<String, Double> totais = pedidoService.obterTotalValorPedidosPorDiaSemanaUltimaSemana();
        return ResponseEntity.ok(totais);
    }

    @GetMapping("/valor-total-dia")
    public ResponseEntity<Double> getValorTotalPedidosDoDia() {
        Double valorTotal = pedidoService.obterValorTotalPedidosDoDia();
        return ResponseEntity.ok(valorTotal);
    }

    @GetMapping("/status-do-dia")
    public ResponseEntity<Map<String, Integer>> getPedidosPorStatusDoDia() {
        Map<String, Integer> pedidosPorStatus = pedidoService.obterPedidosPorStatusDoDia();
        return ResponseEntity.ok(pedidosPorStatus);
    }

    @GetMapping("/top-clientes-mes")
    public ResponseEntity<Map<String, Double>> getTopClientesPorValorPedidoMes() {
        Map<String, Double> topClientes = pedidoService.obterTopClientesPorValorPedidoMes();
        return ResponseEntity.ok(topClientes);
    }

    @GetMapping("/ticket-medio-mes")
    public ResponseEntity<Double> getTicketMedioPedidosMes() {
        double ticketMedio = pedidoService.obterTicketMedioPedidosMes();
        return ResponseEntity.ok(ticketMedio);
    }

    @GetMapping("/bebidas-mais-vendidas-mes")
    public ResponseEntity<Map<String, Integer>> getBebidasMaisVendidasMes() {
        Map<String, Integer> bebidasMaisVendidas = pedidoService.obterBebidasMaisVendidasMes();
        return ResponseEntity.ok(bebidasMaisVendidas);
    }

    @GetMapping("/GerarBrCode/{valor}")
    public String gerarBrCode(@PathVariable double valor) {
        return this.pedidoService.gerarBrCode(valor);
    }

}
