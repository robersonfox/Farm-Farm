package br.com.cleancode.adapters.database.h2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.cleancode.adapters.database.h2.models.FarmModel;
import br.com.cleancode.adapters.database.h2.repository.IFarmRepository;
import br.com.cleancode.usecases.farm.FarmRequest;

@RunWith(MockitoJUnitRunner.class)
public class FarmRepositoryImpTest {
    @InjectMocks
    private FarmRepositoryImp inject;

    @Mock
    private IFarmRepository repository;

    private FarmModel farmModel = new FarmModel(1L, "name 2", null, new Date(), new Date());

    @Before
    public void init() {
        inject = new FarmRepositoryImp(repository);
    }

    @Test
    public void caminhoFeliz() throws Exception {
        inject.insert("name");
    }

    @Test(expected = Exception.class)
    public void deveriaRetornarFarmExiste() throws Exception {
        when(repository.findOne(any())).thenReturn(Optional.of(new FarmModel("name")));

        inject.insert("name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveriaRetornarErroAoSalvar() throws Exception {
        IllegalArgumentException ex = new IllegalArgumentException();
        when(repository.save(any())).thenThrow(ex);

        inject.insert("name");
    }

    @Test
    public void deveriaAtualizarUmRegistro() {
        FarmRequest request = FarmRequest.builder().id(1L).name("name").build();

        when(repository.findById(any())).thenReturn(Optional.of(farmModel));
        when(repository.save(any())).thenReturn(farmModel);

        var model = inject.update(request);

        assertEquals(model.getName(), request.getName());
    }

    @Test(expected = Exception.class)
    public void deveriaRetornarErroAoTentarRemoverPorFaltaDeObjecto() throws Exception {
        inject.delete(1L);
    }

    @Test
    public void deveriaRemoverUmRegistro() throws Exception {
        when(repository.findById(any())).thenReturn(Optional.of(farmModel));

        inject.delete(1L);
    }
}
