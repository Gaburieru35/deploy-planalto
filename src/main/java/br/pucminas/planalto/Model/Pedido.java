package br.pucminas.planalto.Model;

import java.sql.Date;
import java.util.List;
import java.time.LocalDate;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = Pedido.TABLE_NAME)
public class Pedido implements Serializable{
    public static final String TABLE_NAME = "pedido";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idpedido")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    private Usuario usuario;

    @Column(name = "valorpedido")
    private double valorpedido;

    @Column(name = "datapedido")
    private LocalDate data;

    @Column(name = "statuspedido")
    private int status;

    @Column(name = "brCode")
    private String brCode;

    // @ManyToMany
    // @JoinTable(name = "pedido_bebida",
    //     joinColumns = @JoinColumn(name = "idpedido"),
    //     inverseJoinColumns = @JoinColumn(name = "idbebida")
    // )
    // @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<PedidoBebida> itens;

    public Pedido(){}

    public Pedido(Integer id, Usuario usuario, Double valor, LocalDate data, Integer status, String brCode){
        this.id = id;
        this.usuario = usuario;
        this.valorpedido = valor;
        this.data = data;
        this.status = 1;
        this.brCode = brCode;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getValorpedido() {
        return valorpedido;
    }

    public void setValorpedido(double valorpedido) {
        this.valorpedido = valorpedido;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // public List<PedidoBebida> getItens() {
    //     return itens;
    // }

    // public void setItens(List<PedidoBebida> itens) {
    //     this.itens = itens;
    // }

    public String getBrCode() {
        return brCode;
    }

    public void setBrCode(String brCode) {
        this.brCode = brCode;
    }
}
