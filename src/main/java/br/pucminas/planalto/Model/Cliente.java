package br.pucminas.planalto.Model;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = Cliente.TABLE_NAME)
public class Cliente {
    public static final String TABLE_NAME = "cliente";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idcliente")
    private Integer id;

    @NotNull
    @Column(name = "cpfcliente", length = 14, nullable = false)
    private String cpf;

    @NotNull
    @Column(name = "rgcliente", length = 14, nullable = false)
    private String rg;

    @NotNull
    @Column(name = "nomecliente", length = 50, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "profissaocliente", length = 100, nullable = false)
    private String profissao;

    @NotNull
    @Column(name = "ruacliente", length = 200, nullable = false)
    private String rua;

    @NotNull
    @Column(name = "bairrocliente", length = 100, nullable = false)
    private String bairro;

    @NotNull
    @Column(name = "numerocliente", length = 100, nullable = false)
    private int numero;

    @NotNull
    @Column(name = "complementocliente", length = 100, nullable = false)
    private String complemento;

    @OneToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    private Usuario usuario;


    public Cliente(){}

    public Cliente(Integer id, String cpf, String rg, String nome, String profissao, String rua, String bairro, int numero, String complemento){
        this.id = id;
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.profissao = profissao;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getCpf(){
        return this.cpf;
    }

    public void setRg(String rg){
        this.rg = rg;
    }

    public String getRg(){
        return this.rg;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setProfissao(String profissao){
        this.profissao = profissao;
    }

    public String getProfissao(){
        return this.profissao;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }

    public Integer getUserId(){
        return this.usuario.getId();
    }
}