package com.desafio.vendedores.repository;

import com.desafio.vendedores.model.Atuacao;
import org.springframework.data.repository.CrudRepository;

public interface AtuacaoRepository extends CrudRepository<Atuacao, String> {

    public Atuacao findByRegiaoIgnoreCase(String regiao);
}
