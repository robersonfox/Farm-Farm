package br.com.cleancode.usecases.animal;

import java.io.Serializable;

import br.com.cleancode.usecases.farm.FarmResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalResponse implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Long id;
    public String tag;
    private FarmResponse farm;
}
