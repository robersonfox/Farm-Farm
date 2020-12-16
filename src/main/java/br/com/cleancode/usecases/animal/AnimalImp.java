package br.com.cleancode.usecases.animal;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.cleancode.usecases.animal.port.IAnimalPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimalImp implements IAnimal {

    private final IAnimalPort port;

    @Override
    public AnimalResponse insert(AnimalRequest request) throws Exception {

        if (request.getTag() == null) {
            return null;
        }

        var animal = port.insert(request);

        if (animal == null) {
            return null;
        }

        return port.convert(animal);
    }

    @Override
    public boolean insert(List<AnimalRequest> request) throws Exception {
        // verifico se as propriedades foram preenchidas
        if (request.isEmpty()) {
            return false;
        }

        for (AnimalRequest animal : request) {
            if (animal.getFarm() == null || animal.getTag() == null) {
                return false;
            }
        }

        var ok = port.insert(request);

        return ok;
    }

    @Override
    public void delete(AnimalRequest request) throws Exception {
        port.delete(request.getId());
    }

    @Override
    public List<AnimalResponse> all(int page) {
        // Pagina dez em dez
        page = page < 0 ? 0 : page;
        PageRequest pageRequest = PageRequest.of(page, 10);

        return port.all(pageRequest);
    }
}
