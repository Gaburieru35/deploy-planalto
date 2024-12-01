package br.pucminas.planalto.Model;

import java.util.List;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = Bebida.TABLE_NAME)
public class Bebida implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "bebida";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idbebida")
    private Integer id;

    @Nonnull
    @Column(name = "nomebebida", length = 50, nullable = false)
    private String nome;

    @Nonnull
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Nonnull
    @Column(name = "preco", columnDefinition="Decimal(10,2)", nullable = false)
    private Double preco;

    @Nonnull
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    //foto
    @Nonnull
    @Lob
    @Column(name = "foto", nullable = false)
    private String foto;

    @ManyToOne
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
    private Categoria categoria;

    // // @ManyToMany(mappedBy = "itens")
    // @OneToMany(mappedBy = "bebida", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<PedidoBebida> pedido;

    public Bebida(){}

    public Bebida(Integer id, String nome, Double preco, Integer quantidade,Categoria categoria, String descricao, String foto){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.descricao = descricao;
        this.foto = foto;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public Double getPreco(){
        return this.preco;
    }

    public void setPreco(Double preco){
        this.preco = preco;
    }

    public Integer getQuantidade(){
        return this.quantidade;
    }

    public void setQuantidade(Integer quantidade){
        this.quantidade = quantidade;
    }

    // public List<PedidoBebida> getPedido() {
    //     return pedido;
    // }

    // public void setPedido(List<PedidoBebida> pedido) {
    //     this.pedido = pedido;
    // }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
