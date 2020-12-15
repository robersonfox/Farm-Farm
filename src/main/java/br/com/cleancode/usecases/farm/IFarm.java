package br.com.cleancode.usecases.farm;

import java.util.List;

public interface IFarm {
    public FarmResponse getById(FarmRequest request) throws Exception;

    public FarmResponse insert(FarmRequest request) throws Exception;

    public FarmResponse update(FarmRequest request);

    public FarmResponse delete(FarmRequest request);

    public List<FarmResponse> all(FarmRequest request);
}
