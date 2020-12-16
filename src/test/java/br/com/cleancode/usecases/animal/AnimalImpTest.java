package br.com.cleancode.usecases.animal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.cleancode.usecases.animal.port.IAnimalPort;

@RunWith(MockitoJUnitRunner.class)
public class AnimalImpTest {
    @InjectMocks
    private AnimalImp inject;

    @Mock
    private IAnimalPort port;

    private AnimalRequest request = AnimalRequest.builder().id(1L).tag("tag").farm(1L).build();

    @Before
    public void init() {
        inject = new AnimalImp(port);
    }

    @Test
    public void deveriaInserirUmRequest() throws Exception {
        when(port.insert(request)).thenReturn(new Object());

        var retorno = inject.insert(request);

        assertNotNull(retorno);
    }

    @Test
    public void deveriaRetornarErroPorDadosIncompletos() throws Exception {
        request.setTag(null);

        var retorno = inject.insert(request);

        assertNull(retorno);
    }

    @Test
    public void deveriaInserirUmLote() throws Exception {
        when(port.insert(anyList())).thenReturn(true);
        var retorno = inject.insert(Arrays.asList(request));

        assertTrue(retorno);
    }

    @Test
    public void deveriaRemoverUmRegistro() throws Exception {
        inject.delete(request);
    }

    @Test
    public void deveriaRetornarUmaLista() {
        AnimalResponse animalResponse = AnimalResponse.builder().build();

        when(port.all(any())).thenReturn(Arrays.asList(animalResponse));
        var retorno = inject.all(1);

        assertNotNull(retorno.get(0));
    }
}
