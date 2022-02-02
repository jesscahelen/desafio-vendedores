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


    public String add(VendedorDTO vendedorDTO) {

        Vendedor vendedor = dtoToEntity(vendedorDTO);
        if (vendedorDTO.getNome() != null &&
                vendedorDTO.getTelefone() != null &&
                vendedorDTO.getIdade() != null &&
                vendedorDTO.getCidade() != null &&
                vendedorDTO.getEstado() != null &&
                vendedorDTO.getRegiao() != null)
        {
            Atuacao atuacao = atuacaoRepository.findByRegiaoIgnoreCase(vendedorDTO.getRegiao());
            if (atuacao == null) {
                return "Região indicada não existe.";
            }
            vendedor.setRegiao(atuacao);
            vendedor.setDataInclusao(LocalDate.now());
            vendedorRepository.save(vendedor);
            return "Ok";
        }

        return "Os campos 'nome', 'telefone', 'idade', 'cidade', 'estado', 'regiao' são obrigatórios.";
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
        if (vendedor.getRegiao() != null) {
            vendedorDTO.setEstados(vendedor.getRegiao().getEstados());
        }

        return vendedorDTO;
    }

    public VendedorDTO entityToDtoFullInfo(Vendedor vendedor) {
        VendedorDTO vendedorDTO = new VendedorDTO();

        vendedorDTO.setNome(vendedor.getNome());
        vendedorDTO.setTelefone(vendedor.getTelefone());
        vendedorDTO.setIdade(vendedor.getIdade());
        vendedorDTO.setCidade(vendedor.getCidade());
        vendedorDTO.setEstado(vendedor.getEstado());
        if (vendedor.getRegiao() != null) {
            vendedorDTO.setEstados(vendedor.getRegiao().getEstados());
        }
        return vendedorDTO;
    }

    public String localDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
