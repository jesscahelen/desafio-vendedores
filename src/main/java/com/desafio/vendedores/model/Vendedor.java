package com.desafio.vendedores.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vendedor")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_inclusao")
    private LocalDate dataInclusao;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "regiao", nullable = false)
    private Atuacao regiao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDate dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Atuacao getRegiao() {
        return regiao;
    }

    public void setRegiao(Atuacao regiao) {
        this.regiao = regiao;
    }
}
