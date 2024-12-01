// package br.pucminas.planalto.Controller;

// import br.pucminas.planalto.Model.ItensCarrinho;
// import br.pucminas.planalto.Services.CarrinhoService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/itenscarrinho")
// public class ItensCarrinhoController {

// @Autowired
// private CarrinhoService carrinhoService;

// @PostMapping("/{idCarrinho}/adicionar")
// public ResponseEntity<ItensCarrinho> adicionarItem(
// @PathVariable Long idCarrinho, @RequestBody ItensCarrinho novoItem) {
// ItensCarrinho itemAdicionado = carrinhoService.adicionarItem(idCarrinho,
// novoItem);
// return ResponseEntity.ok(itemAdicionado);
// }

// @DeleteMapping("/{idItem}")
// public ResponseEntity<Void> removerItem(@PathVariable Long idItem) {
// carrinhoService.removerItem(idItem);
// return ResponseEntity.noContent().build();
// }

// @PutMapping("/{idItem}/atualizar")
// public ResponseEntity<ItensCarrinho> atualizarQuantidade(
// @PathVariable Long idItem, @RequestParam int novaQuantidade) {
// ItensCarrinho itemAtualizado = carrinhoService.atualizarQuantidade(idItem,
// novaQuantidade);
// return ResponseEntity.ok(itemAtualizado);
// }

// @GetMapping("/{idCarrinho}")
// public ResponseEntity<List<ItensCarrinho>>
// listarItensDoCarrinho(@PathVariable Long idCarrinho) {
// List<ItensCarrinho> itens =
// carrinhoService.listarItensDoCarrinho(idCarrinho);
// return ResponseEntity.ok(itens);
// }
// }
