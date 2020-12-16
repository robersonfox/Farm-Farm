package br.com.cleancode.adapters.database.h2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import br.com.cleancode.adapters.database.h2.models.AnimalsModel;
import br.com.cleancode.adapters.database.h2.models.FarmModel;
import br.com.cleancode.adapters.database.h2.repository.IAnimalRepository;
import br.com.cleancode.adapters.database.h2.repository.IFarmRepository;
import br.com.cleancode.usecases.animal.AnimalRequest;
import br.com.cleancode.usecases.animal.AnimalResponse;

@RunWith(MockitoJUnitRunner.class)
public class AnimalRepositoryImpTest {
    @InjectMocks
    private AnimalRepositoryImp inject;

    @Mock
    private IAnimalRepository repository;

    @Mock
    private IFarmRepository farmReposirory;

    @Before
    public void init() {
        inject = new AnimalRepositoryImp(repository, farmReposirory);
    }

    @Test
    public void caminhoFeliz() throws Exception {
        AnimalRequest request = AnimalRequest.builder().id(1L).tag("tag").farm(1L).build();

        when(farmReposirory.findById(any())).thenReturn(Optional.of(new FarmModel()));

        inject.insert(request);
    }

    @Test(expected = Exception.class)
    public void deveriaRetornarErrorPorAnimalExistir() throws Exception {
        AnimalRequest request = AnimalRequest.builder().id(1L).tag("tag").farm(1L).build();

        when(repository.findOne(any())).thenReturn(Optional.of(new AnimalsModel()));

        inject.insert(request);
    }

    @Test(expected = Exception.class)
    public void deveriaRetornarErrorPorFaltaDeFarm() throws Exception {
        AnimalRequest request = AnimalRequest.builder().id(1L).tag("tag").farm(1L).build();

        inject.insert(request);
    }

    @Test
    public void deveriaInserirUmLote() throws Exception {
        List<AnimalRequest> animals = new ArrayList<>();

        AnimalRequest animal = AnimalRequest.builder().id(1L).tag("tag").farm(1L).build();
        animals.add(animal);

        when(farmReposirory.findById(any())).thenReturn(Optional.of(new FarmModel()));

        inject.insert(animals);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveriaRetornarUmaExceptionAoTentarSalvar() throws Exception {
        List<AnimalRequest> animals = new ArrayList<>();

        AnimalRequest animal = AnimalRequest.builder().id(1L).tag("tag").farm(1L).build();
        animals.add(animal);

        when(farmReposirory.findById(any())).thenReturn(Optional.of(new FarmModel()));

        var exp = new IllegalArgumentException();
        when(repository.saveAll(any())).thenThrow(exp);

        inject.insert(animals);
    }

    @Test
    public void deveriaRemoverUmRegistro() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new AnimalsModel()));
        inject.delete(1L);
    }

    @Test
    public void deveriaConverterUmObjecto() {
        AnimalsModel model = AnimalsModel.builder().build();
        AnimalResponse response = inject.convert(model);

        assertEquals(response.getClass().getSimpleName(), AnimalResponse.class.getSimpleName());
    }

    @Test
    public void deveriaGarantirOsDadosDoObjectoConvertido() {
        AnimalsModel model = AnimalsModel.builder().id(1L).tag("tag").build();

        AnimalResponse response = inject.convert(model);

        assertEquals(response.getTag(), model.getTag());
        assertEquals(response.getId(), model.getId());
    }

    @Test
    public void deveriaRetornarUmaListaPopulada() {
        PageRequest pageRequest = PageRequest.of(0, 1);

        org.springframework.data.domain.Page<AnimalsModel> pages = org.springframework.data.domain.Page.empty();
        when(repository.findAll(pageRequest)).thenReturn(pages);

        inject.all(pageRequest);
    }
}
