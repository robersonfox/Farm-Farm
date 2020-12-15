package br.com.cleancode.usecases.farm;

import java.util.List;

import br.com.cleancode.adapters.database.h2.models.AnimalsModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FarmResponse {
    private Long id;
    private String name;
    private List<AnimalsModel> animals;
}
