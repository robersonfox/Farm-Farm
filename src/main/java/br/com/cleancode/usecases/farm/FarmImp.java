package br.com.cleancode.usecases.farm;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cleancode.usecases.farm.port.IFarmPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FarmImp implements IFarm {

    private final IFarmPort port;

    @Override
    public FarmResponse getById(FarmRequest request) throws Exception {
        if (request.getId() == null || request.getId() <= 0) {
            return null;
        }

        Long id = request.getId();

        var farm = port.getById(id);

        if (farm == null) {
            return null;
        }

        return port.convert(farm);
    }

    @Override
    public FarmResponse insert(FarmRequest request) throws Exception {
        if (request.getName() == null) {
            return null;
        }

        String name = request.getName();

        var farm = port.insert(name);

        if (farm == null) {
            return null;
        }

        return port.convert(farm);
    }

    @Override
    public FarmResponse update(FarmRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FarmResponse delete(FarmRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<FarmResponse> all(FarmRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

}
