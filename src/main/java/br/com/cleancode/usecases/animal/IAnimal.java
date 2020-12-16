package br.com.cleancode.usecases.animal;

import java.util.List;

public interface IAnimal {
    public AnimalResponse insert(AnimalRequest request) throws Exception;

    public boolean insert(List<AnimalRequest> request) throws Exception;

    public void delete(AnimalRequest request) throws Exception;

    public List<AnimalResponse> all(int page);
}
