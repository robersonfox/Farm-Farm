package br.com.cleancode.usecases.farm.port;

import java.util.List;

import br.com.cleancode.usecases.farm.FarmRequest;
import br.com.cleancode.usecases.farm.FarmResponse;

public interface IFarmPort<T> {
    public T getById(Long request) throws Exception;

    public T insert(String request) throws Exception;

    public T update(FarmRequest request);

    public void delete(Long id) throws Exception;

    public List<T> all(FarmRequest request);

    public FarmResponse convert(T model);
}
