// package br.pucminas.planalto.Model;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "itens_carrinho")
// public class ItensCarrinho {

// @ManyToOne
// @JoinColumn(name = "id_carrinho")
// private Carrinho carrinho;

// @ManyToOne
// @JoinColumn(name = "id_bebida")
// private Bebida bebida;

// @Column(name = "quantidade")
// private int quantidade;

// @Column(name = "preco")
// private double preco;

// public ItensCarrinho() {
// }

// public ItensCarrinho(Carrinho carrinho, Bebida bebida, int quantidade, double
// preco) {
// this.carrinho = carrinho;
// this.bebida = bebida;
// this.quantidade = quantidade;
// this.preco = preco;
// }

// public Carrinho getCarrinho() {
// return carrinho;
// }

// public void setCarrinho(Carrinho carrinho) {
// this.carrinho = carrinho;
// }

// public Bebida getBebida() {
// return bebida;
// }

// public void setBebida(Bebida bebida) {
// this.bebida = bebida;
// }

// public int getQuantidade() {
// return quantidade;
// }

// public void setQuantidade(int quantidade) {
// this.quantidade = quantidade;
// }

// public double getPreco() {
// return preco;
// }

// public void setPreco(double preco) {
// this.preco = preco;
// }
// }
