package br.com.cleancode.usecases.farm;

import java.util.List;

public interface IFarm {
    public FarmResponse getById(Long id) throws Exception;

    public FarmResponse insert(FarmRequest request) throws Exception;

    public FarmResponse update(FarmRequest request);

    public void delete(FarmRequest request) throws Exception;

    public List<FarmResponse> all();
}
