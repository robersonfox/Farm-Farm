package br.com.cleancode.adapters.database.h2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.cleancode.adapters.database.h2.models.AnimalsModel;
import br.com.cleancode.adapters.database.h2.models.FarmModel;
import br.com.cleancode.adapters.database.h2.repository.IAnimalRepository;
import br.com.cleancode.adapters.database.h2.repository.IFarmRepository;
import br.com.cleancode.usecases.animal.AnimalRequest;
import br.com.cleancode.usecases.animal.AnimalResponse;
import br.com.cleancode.usecases.animal.port.IAnimalPort;
import br.com.cleancode.usecases.farm.FarmResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class AnimalRepositoryImp implements IAnimalPort<AnimalsModel> {
    private static final String EXISTE = "Tag já cadastrada";
    private static final String IMPOSSIVEL_INSERIR = "Não foi possível inserir";
    private static final String FARM_NAO_EXISTE = "Não foi possível inserir";

    private final IAnimalRepository repository;

    private final IFarmRepository farmReposirory;

    @Override
    public AnimalsModel insert(AnimalRequest request) throws Exception {
        // Existe animal?
        var opAnimal = existAnimal(request);
        if (opAnimal.isPresent()) {
            log.error(EXISTE);
            throw new Exception(EXISTE);
        }

        // Existe a fazenda?
        var opFarm = getFarm(request);
        if (!opFarm.isPresent()) {
            log.error(FARM_NAO_EXISTE);
            throw new Exception(FARM_NAO_EXISTE);
        }

        AnimalsModel model = AnimalsModel.builder().tag(request.getTag()).farm(opFarm.get()).build();

        try {
            return repository.save(model);
        } catch (Exception e) {
            log.error(IMPOSSIVEL_INSERIR);
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        var entity = repository.findById(id);

        if (entity.isPresent()) {
            repository.delete(entity.get());
        }
    }

    @Override
    public AnimalResponse convert(AnimalsModel model) {
        return AnimalResponse.builder().tag(model.getTag()).id(model.getId()).build();
    }

    @Override
    public List<AnimalResponse> all(PageRequest pageRequest) {
        var pages = repository.findAll(pageRequest);

        List<AnimalResponse> response = new ArrayList<>();

        pages.forEach(model -> {
            var farm = FarmResponse.builder().id(model.getFarm().getId()).name(model.getFarm().getName()).build();
            var animal = AnimalResponse.builder().id(model.getId()).tag(model.getTag()).farm(farm).build();
            response.add(animal);
        });

        return response;
    }

    private Optional<FarmModel> getFarm(AnimalRequest request) throws Exception {
        return farmReposirory.findById(request.getFarm());
    }

    private Optional<AnimalsModel> existAnimal(AnimalRequest request) throws Exception {
        Example<AnimalsModel> example = Example.of(new AnimalsModel(request.getTag()));
        return repository.findOne(example);
    }
}
