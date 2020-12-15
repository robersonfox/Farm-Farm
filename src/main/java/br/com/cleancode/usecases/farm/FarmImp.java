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
    public FarmResponse getById(Long id) throws Exception {
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
        return null;
    }

    @Override
    public void delete(FarmRequest request) throws Exception {
        port.delete(request.getId());
    }

    @Override
    public List<FarmResponse> all(FarmRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

}
