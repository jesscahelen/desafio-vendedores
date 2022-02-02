package com.desafio.vendedores.service;

import com.desafio.vendedores.model.Atuacao;
import com.desafio.vendedores.model.Vendedor;
import com.desafio.vendedores.repository.AtuacaoRepository;
import com.desafio.vendedores.repository.VendedorRepository;
import com.desafio.vendedores.service.dto.VendedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private AtuacaoRepository atuacaoRepository;


    public Vendedor add(VendedorDTO vendedorDTO) {

        Vendedor vendedor = dtoToEntity(vendedorDTO);
        vendedor.setDataInclusao(LocalDate.now());
        Atuacao atuacao = atuacaoRepository.findByRegiaoIgnoreCase(vendedorDTO.getRegiao());
        if (atuacao == null) {
            return null;
        }
        vendedor.setRegiao(atuacao);
        vendedorRepository.save(vendedor);

        return vendedor;
    }

    public VendedorDTO findById(Long id) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(id);

        if (vendedor.isPresent()) {
            return entityToDtoBasicInfo(vendedor.get());
        }

        return null;
    }

    public List<VendedorDTO> findAll() {

        List<Vendedor> vendedores = (List<Vendedor>) vendedorRepository.findAll();
        List<VendedorDTO> vendedoresDTO = new ArrayList<>();
        vendedores.forEach(vendedor -> {
            vendedoresDTO.add(entityToDtoFullInfo(vendedor));
        });
        return vendedoresDTO;
    }

    public Vendedor dtoToEntity(VendedorDTO vendedorDTO) {

        Vendedor vendedor = new Vendedor();
        vendedor.setNome(vendedorDTO.getNome());
        vendedor.setTelefone(vendedorDTO.getTelefone());
        vendedor.setIdade(vendedorDTO.getIdade());
        vendedor.setCidade(vendedorDTO.getCidade());
        vendedor.setEstado(vendedorDTO.getEstado());

        return vendedor;
    }

    public VendedorDTO entityToDtoBasicInfo(Vendedor vendedor) {
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome(vendedor.getNome());
        vendedorDTO.setDataInclusao(localDateToString(vendedor.getDataInclusao()));
        vendedorDTO.setEstados(vendedor.getRegiao().getEstados());

        return vendedorDTO;
    }

    public VendedorDTO entityToDtoFullInfo(Vendedor vendedor) {
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome(vendedor.getNome());
        vendedorDTO.setTelefone(vendedor.getTelefone());
        vendedorDTO.setIdade(vendedor.getIdade());
        vendedorDTO.setCidade(vendedor.getCidade());
        vendedorDTO.setEstado(vendedor.getEstado());
        vendedorDTO.setEstados(vendedor.getRegiao().getEstados());

        return vendedorDTO;
    }

    public String localDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
