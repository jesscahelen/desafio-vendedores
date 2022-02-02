package com.desafio.vendedores.repository;

import com.desafio.vendedores.model.Vendedor;
import org.springframework.data.repository.CrudRepository;

public interface VendedorRepository extends CrudRepository<Vendedor, Long> {
}
