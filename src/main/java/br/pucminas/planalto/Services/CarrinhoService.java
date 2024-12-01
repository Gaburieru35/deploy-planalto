// package br.pucminas.planalto.Services;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import br.pucminas.planalto.Model.Carrinho;
// import br.pucminas.planalto.Model.ItensCarrinho;
// import br.pucminas.planalto.Repositories.CarrinhoRepository;
// import br.pucminas.planalto.Repositories.ItensCarrinhoRepository;

// @Service
// public class CarrinhoService {
// @Autowired
// private CarrinhoRepository carrinhoRepository;

// @Autowired
// private ItensCarrinhoRepository itensCarrinhoRepository;

// public Carrinho criarCarrinho(Carrinho carrinho) {
// return carrinhoRepository.save(carrinho);
// }

// public Optional<Carrinho> buscarPorId(Long id) {
// return carrinhoRepository.findById(id);
// }

// public ItensCarrinho adicionarItem(Long idCarrinho, ItensCarrinho novoItem) {
// Carrinho carrinho = carrinhoRepository.findById(idCarrinho)
// .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

// List<ItensCarrinho> itensExistentes =
// itensCarrinhoRepository.findByCarrinho(carrinho);
// Optional<ItensCarrinho> itemExistente = itensExistentes.stream()
// .filter(item -> item.getBebida().equals(novoItem.getBebida()))
// .findFirst();

// if (itemExistente.isPresent()) {
// ItensCarrinho item = itemExistente.get();
// item.setQuantidade(item.getQuantidade() + novoItem.getQuantidade());
// return itensCarrinhoRepository.save(item);
// } else {
// novoItem.setCarrinho(carrinho);
// return itensCarrinhoRepository.save(novoItem);
// }
// }

// public void removerItem(Long idItem) {
// itensCarrinhoRepository.deleteById(idItem);
// }

// public ItensCarrinho atualizarQuantidade(Long idItem, int novaQuantidade) {
// ItensCarrinho item = itensCarrinhoRepository.findById(idItem)
// .orElseThrow(() -> new RuntimeException("Item não encontrado"));

// item.setQuantidade(novaQuantidade);
// return itensCarrinhoRepository.save(item);
// }

// public List<ItensCarrinho> listarItensDoCarrinho(Long idCarrinho) {
// Carrinho carrinho = carrinhoRepository.findById(idCarrinho)
// .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
// return itensCarrinhoRepository.findByCarrinho(carrinho);
// }

// public double calcularTotal(Long idCarrinho) {
// List<ItensCarrinho> itens = listarItensDoCarrinho(idCarrinho);
// return itens.stream()
// .mapToDouble(item -> item.getBebida().getPreco() * item.getQuantidade())
// .sum();
// }

// public void esvaziarCarrinho(Long idCarrinho) {
// Carrinho carrinho = carrinhoRepository.findById(idCarrinho)
// .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
// List<ItensCarrinho> itens = itensCarrinhoRepository.findByCarrinho(carrinho);
// itensCarrinhoRepository.deleteAll(itens);
// }

// }
