package br.com.cleancode.usecases.farm;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cleancode.usecases.animal.AnimalResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FarmResponse implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    @JsonIgnore
    private List<AnimalResponse> animals;
}
