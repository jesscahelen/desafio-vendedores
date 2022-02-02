import com.desafio.vendedores.model.Atuacao;
import com.desafio.vendedores.model.Vendedor;
import com.desafio.vendedores.service.VendedorService;
import com.desafio.vendedores.service.dto.VendedorDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class VendedorTest {

    @InjectMocks
    VendedorService vendedorService;

    @Test
    public void givenCorrectLocalDate_convertLocalDateToString() {
        LocalDate localDate = LocalDate.now();
        String dateString = vendedorService.localDateToString(localDate);
        Assert.assertTrue(!dateString.isEmpty());
        Assert.assertTrue(dateString.matches("^\\d{2}/\\d{2}/\\d{4}$"));
    }

    @Test
    public void givenCorrectEntity_entityToDtoFullInfo() {
        Vendedor vendedor = new Vendedor();
        vendedor.setNome("Fulano de Tal");
        vendedor.setTelefone("99 99999 99999");
        vendedor.setIdade(55);
        vendedor.setCidade("São Paulo");
        vendedor.setEstado("São Paulo");
        vendedor.setRegiao(new Atuacao("sul", new String[]{"PR", "RS", "SC"}));

        VendedorDTO vendedorDTO = vendedorService.entityToDtoFullInfo(vendedor);

        Assert.assertTrue(vendedorDTO.getEstados().length == 3);
    }

    @Test
    public void givenUncompletedEntity_entityToDtoFullInfo() {
        Vendedor vendedor = new Vendedor();
        vendedor.setNome("Fulano de Tal");
        vendedor.setTelefone("99 99999 99999");
        vendedor.setIdade(55);
        vendedor.setCidade("São Paulo");
        vendedor.setEstado("São Paulo");
        vendedor.setRegiao(null);

        VendedorDTO vendedorDTO = vendedorService.entityToDtoFullInfo(vendedor);

        Assert.assertTrue(vendedorDTO.getEstados() == null);
    }

    @Test
    public void givenCorrectEntity_entityToDtoBasicInfo() {
        Vendedor vendedor = new Vendedor();
        vendedor.setNome("Fulano de Tal");
        vendedor.setDataInclusao(LocalDate.now());
        vendedor.setRegiao(new Atuacao("sul", new String[]{"PR", "RS", "SC"}));

        VendedorDTO vendedorDTO = vendedorService.entityToDtoBasicInfo(vendedor);

        Assert.assertTrue(vendedorDTO.getEstados().length == 3);
        Assert.assertTrue(!vendedorDTO.getDataInclusao().isEmpty());
    }

    @Test
    public void givenUncompletedEntity_entityToDtoBasicInfo() {
        Vendedor vendedor = new Vendedor();
        vendedor.setNome("Fulano de Tal");
        vendedor.setDataInclusao(LocalDate.now());
        vendedor.setRegiao(null);

        VendedorDTO vendedorDTO = vendedorService.entityToDtoBasicInfo(vendedor);

        Assert.assertTrue(vendedorDTO.getEstados() == null);
    }

    @Test
    public void givenUncompletedEntity_add() {
        VendedorDTO vendedorDTO = new VendedorDTO();

        vendedorDTO.setNome("Sicrano Silva");
        vendedorDTO.setTelefone("99 99999 99999");
        vendedorDTO.setIdade(55);

        String result = vendedorService.add(vendedorDTO);

        Assert.assertTrue(result
                .equals("Os campos 'nome', 'telefone', 'idade', 'cidade', 'estado', 'regiao' são obrigatórios."));
    }

}
