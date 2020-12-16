package br.com.cleancode.adapters.database.h2.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.cleancode.adapters.database.h2.models.AnimalsModel;
import br.com.cleancode.adapters.database.h2.models.FarmModel;
import br.com.cleancode.adapters.database.h2.repository.IFarmRepository;
import br.com.cleancode.usecases.animal.AnimalResponse;
import br.com.cleancode.usecases.farm.FarmRequest;
import br.com.cleancode.usecases.farm.FarmResponse;
import br.com.cleancode.usecases.farm.port.IFarmPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class FarmRepositoryImp implements IFarmPort<FarmModel> {

    private static final String NAO_ENCONTRADO = "Nenhum registro encontrado";
    private static final String IMPOSSIVEL_INSERIR = "Não foi possível inserir";
    private static final String IMPOSSIVEL_ATUALIZAR = "Não foi possível atualizar";
    private static final String EXISTE = "Registro já existe";

    private final IFarmRepository repository;

    @Override
    public FarmModel getById(Long id) throws Exception {
        try {
            return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NAO_ENCONTRADO));

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public FarmModel insert(String name) throws Exception {
        // Verifica se existe
        Example<FarmModel> example = Example.of(new FarmModel(name));
        var existModel = repository.findOne(example);

        if (existModel.isPresent()) {
            log.error(EXISTE);
            throw new Exception(EXISTE);
        }

        FarmModel model = FarmModel.builder().name(name).build();

        try {
            return repository.save(model);
        } catch (Exception e) {
            log.error(IMPOSSIVEL_INSERIR);
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public FarmModel update(FarmRequest request) {
        FarmModel existModel = null;

        try {
            // Verifica se existe
            existModel = getById(request.getId());

        } catch (Exception e1) {
            log.error(NAO_ENCONTRADO);
            throw new IllegalArgumentException(e1.getMessage());
        }

        // ajusto as propriedades que deverão ser atualizadas
        existModel.setName(request.getName());

        try {

            return repository.save(existModel);
        } catch (Exception e) {
            log.error(IMPOSSIVEL_ATUALIZAR);
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        // Verifica se existe
        var existModel = getById(id);

        if (existModel != null) {
            repository.delete(existModel);
        }
    }

    @Override
    public List<FarmModel> all(PageRequest pageable) {
        var pages = repository.findAll(pageable);

        return pages.getContent();
    }

    @Override
    public FarmResponse convert(FarmModel model) {
        List<AnimalResponse> animals = new ArrayList<>();

        if (model.getAnimals() != null && !model.getAnimals().isEmpty()) {
            for (AnimalsModel modelAnimals : model.getAnimals()) {
                var animal = AnimalResponse.builder().id(modelAnimals.getId()).tag(modelAnimals.getTag()).build();

                animals.add(animal);
            }
        }

        return FarmResponse.builder().id(model.getId()).name(model.getName()).animals(animals).build();
    }
}
