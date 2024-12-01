// package br.pucminas.planalto.Model;

// import java.util.List;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "Carrinho")
// public class Carrinho {

// @Id
// @GeneratedValue(strategy = GenerationType.SEQUENCE)
// @Column(name = "idCarrinho")
// private Long idCarrinho;

// @ManyToOne
// @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
// private Cliente cliente;

// private List<ItensCarrinho> itens;

// public Carrinho(Long id, Cliente cliente) {
// this.idCarrinho = id;
// this.cliente = cliente;
// }

// public Long getId() {
// return idCarrinho;
// }

// public void setId(Long id) {
// this.idCarrinho = id;
// }

// public Cliente getCliente() {
// return cliente;
// }

// public void setCliente(Cliente cliente) {
// this.cliente = cliente;
// }

// public List<ItensCarrinho> getItens() {
// return itens;
// }

// public void setItens(List<ItensCarrinho> itens) {
// this.itens = itens;
// }

// }
