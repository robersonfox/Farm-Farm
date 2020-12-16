package br.com.cleancode.usecases.animal.port;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import br.com.cleancode.usecases.animal.AnimalRequest;
import br.com.cleancode.usecases.animal.AnimalResponse;

public interface IAnimalPort<T> {
    public T insert(AnimalRequest request) throws Exception;

    public void delete(Long id) throws Exception;

    public AnimalResponse convert(T model);

	public List<AnimalResponse> all(PageRequest pageRequest);
}
