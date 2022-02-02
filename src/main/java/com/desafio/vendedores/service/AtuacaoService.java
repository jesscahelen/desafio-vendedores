package com.desafio.vendedores.service;

import com.desafio.vendedores.model.Atuacao;
import com.desafio.vendedores.repository.AtuacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AtuacaoService {

    @Autowired
    private AtuacaoRepository atuacaoRepository;

    public Atuacao add(Atuacao atuacao) {
        Atuacao atuacaoAdded = atuacaoRepository.save(atuacao);
        return atuacaoAdded;
    }
}
