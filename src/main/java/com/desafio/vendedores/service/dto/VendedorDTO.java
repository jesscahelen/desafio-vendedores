package com.desafio.vendedores.service.dto;

import com.desafio.vendedores.view.View;
import com.fasterxml.jackson.annotation.JsonView;

public class VendedorDTO {

    @JsonView({View.VendedorCompleteInfo.class, View.VendedorBasicInfo.class})
    private String nome;

    @JsonView(View.VendedorCompleteInfo.class)
    private String telefone;

    @JsonView(View.VendedorCompleteInfo.class)
    private Integer idade;

    @JsonView(View.VendedorCompleteInfo.class)
    private String cidade;

    @JsonView(View.VendedorCompleteInfo.class)
    private String estado;

    private String regiao;

    @JsonView(View.VendedorBasicInfo.class)
    private String dataInclusao;

    @JsonView({View.VendedorCompleteInfo.class, View.VendedorBasicInfo.class})
    private String[] estados;

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

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(String dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String[] getEstados() {
        return estados;
    }

    public void setEstados(String[] estados) {
        this.estados = estados;
    }
}
