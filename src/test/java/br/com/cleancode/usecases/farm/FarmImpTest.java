package br.com.cleancode.usecases.farm;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.cleancode.usecases.farm.port.IFarmPort;

@RunWith(MockitoJUnitRunner.class)
public class FarmImpTest {
    @InjectMocks
    private FarmImp inject;

    @Mock
    private IFarmPort port;

    private FarmRequest request = FarmRequest.builder().id(1L).name("name").build();

    @Before
    public void init() {
        inject = new FarmImp(port);
    }

    @Test
    public void deveriaInserir() throws Exception {
        when(port.insert(anyString())).thenReturn(new Object());

        inject.insert(request);
    }

    @Test
    public void deveriaRetornarErroPorFaltaDeParametro() throws Exception {
        when(port.insert(anyString())).thenReturn(new Object());

        request.setName(null);

        var retorno = inject.insert(request);

        assertNull(retorno);
    }

    @Test
    public void deveriaAtualizar() {
        when(port.update(any())).thenReturn(new Object());

        inject.update(request);
    }

    @Test
    public void deveriaRemover() throws Exception {
        inject.delete(request);
    }
}
