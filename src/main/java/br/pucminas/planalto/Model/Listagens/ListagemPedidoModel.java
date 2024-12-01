package br.pucminas.planalto.Model.Listagens;

import java.time.LocalDate;

public class ListagemPedidoModel {
    private Long id;
    private Double valorPedido;
    private String nome;
    private LocalDate data; 
    private String nomeCliente;
    private Integer statusPedido;
    private String brCode;
    private String enderecoCliente;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(Double valorPedido) {
        this.valorPedido = valorPedido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(Integer statusPedido) {
        this.statusPedido = statusPedido;
    }

    // Construtor para o JPA
    public ListagemPedidoModel(Long id, Double valorpedido, String nome, LocalDate data, String nomeCliente, Integer statusPedido, String brCode, String enderecoCliente) {
        this.id = id;
        this.valorPedido = valorpedido;
        this.nome = nome;
        this.data = data;
        this.nomeCliente = nomeCliente;
        this.statusPedido = statusPedido;
        this.brCode = brCode;
        this.enderecoCliente = enderecoCliente;
    }

    public String getBrCode() {
        return brCode;
    }

    public void setBrCode(String brCode) {
        this.brCode = brCode;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    // Getters e setters
}

