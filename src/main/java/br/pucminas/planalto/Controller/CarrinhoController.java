// package br.pucminas.planalto.Controller;

// import br.pucminas.planalto.Model.Carrinho;
// import br.pucminas.planalto.Services.CarrinhoService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Optional;

// @RestController
// @RequestMapping("/carrinhos")
// public class CarrinhoController {

// @Autowired
// private CarrinhoService carrinhoService;

// @PostMapping
// public ResponseEntity<Carrinho> criarCarrinho(@RequestBody Carrinho carrinho)
// {
// Carrinho novoCarrinho = carrinhoService.criarCarrinho(carrinho);
// return ResponseEntity.ok(novoCarrinho);
// }

// @GetMapping("/{id}")
// public ResponseEntity<Carrinho> buscarCarrinhoPorId(@PathVariable Long id) {
// Optional<Carrinho> carrinho = carrinhoService.buscarPorId(id);
// return carrinho.map(ResponseEntity::ok).orElseGet(() ->
// ResponseEntity.notFound().build());
// }

// @DeleteMapping("/{id}/esvaziar")
// public ResponseEntity<Void> esvaziarCarrinho(@PathVariable Long id) {
// carrinhoService.esvaziarCarrinho(id);
// return ResponseEntity.noContent().build();
// }

// @GetMapping("/{id}/total")
// public ResponseEntity<Double> calcularTotal(@PathVariable Long id) {
// double total = carrinhoService.calcularTotal(id);
// return ResponseEntity.ok(total);
// }

// }
