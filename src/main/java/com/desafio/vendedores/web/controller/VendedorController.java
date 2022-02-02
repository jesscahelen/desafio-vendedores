package com.desafio.vendedores.web.controller;

import com.desafio.vendedores.model.Vendedor;
import com.desafio.vendedores.service.VendedorService;
import com.desafio.vendedores.service.dto.VendedorDTO;
import com.desafio.vendedores.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @PostMapping(value = "/vendedor")
    public ResponseEntity<String> add(@RequestBody VendedorDTO vendedorDTO) {

        Vendedor vendedor = vendedorService.add(vendedorDTO);
        if (vendedor == null) {
            return new ResponseEntity<String>("Região indicada não existe.", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @JsonView(View.VendedorBasicInfo.class)
    @GetMapping(value = "/vendedor/{id}")
    public ResponseEntity<VendedorDTO> findById(@PathVariable Long id) {

        VendedorDTO vendedor = vendedorService.findById(id);
        if (vendedor == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<VendedorDTO>(vendedor, HttpStatus.OK);
    }

    @JsonView(View.VendedorCompleteInfo.class)
    @GetMapping(value = "/vendedor")
    public ResponseEntity<List<VendedorDTO>> findAll() {

        List<VendedorDTO> vendedores = vendedorService.findAll();
        if (vendedores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<VendedorDTO>>(vendedores, HttpStatus.OK);
    }
}
