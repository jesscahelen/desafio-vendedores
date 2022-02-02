package com.desafio.vendedores.web.controller;

import com.desafio.vendedores.model.Atuacao;

import com.desafio.vendedores.service.AtuacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AtuacaoController {

    @Autowired
    private AtuacaoService atuacaoService;

    @PostMapping(value = "/atuacao")
    public ResponseEntity<String> add(@RequestBody Atuacao atuacao) {

        Atuacao atuacaoAdded = atuacaoService.add(atuacao);
        if (atuacaoAdded != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Região e Estados são campos obrigatórios.", HttpStatus.NOT_ACCEPTABLE);
    }
}
