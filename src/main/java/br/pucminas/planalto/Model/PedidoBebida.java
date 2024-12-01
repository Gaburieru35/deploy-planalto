package br.pucminas.planalto.Model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = PedidoBebida.TABLE_NAME)
public class PedidoBebida implements java.io.Serializable{
    public static final String TABLE_NAME = "pedido_bebida";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idpedidobebida")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idbebida", referencedColumnName = "idbebida")
    private Bebida bebida;

    @Column(name = "quantidade")
    private Integer quantidade;

    public PedidoBebida(){}

    public PedidoBebida(Integer id, Pedido pedido, Bebida bebida, Integer quantidade){
        this.id = id;
        this.pedido = pedido;
        this.bebida = bebida;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
