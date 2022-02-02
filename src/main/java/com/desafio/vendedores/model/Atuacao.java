package com.desafio.vendedores.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;


@Entity
@Table(name = "atuacao")
@TypeDef(name = "string-array", typeClass = StringArrayType.class)
public class Atuacao {

    @Id
    @Column(name = "regiao")
    private String regiao;

    @Type( type = "string-array" )
    @Column(name = "estados", columnDefinition = "text[]")
    private String[] estados;

    public Atuacao(String regiao, String[] estados) {
        this.regiao = regiao;
        this.estados = estados;
    }

    public Atuacao() {
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String[] getEstados() {
        return estados;
    }

    public void setEstados(String[] estados) {
        this.estados = estados;
    }


}
