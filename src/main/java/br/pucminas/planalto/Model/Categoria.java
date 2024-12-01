package br.pucminas.planalto.Model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = Categoria.TABLE_NAME)
public class Categoria {
    public static final String TABLE_NAME = "categoria";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idcategoria")
    private Integer id;

    @Nonnull
    @Column(name = "nomecategoria", length = 50, nullable = false)
    private String nome;

    public Categoria(){}

    public Categoria(Integer id, String nome){
        this.id = id;
        this.nome = nome;
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
}
