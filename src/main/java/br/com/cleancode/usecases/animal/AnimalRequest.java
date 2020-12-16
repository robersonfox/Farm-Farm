package br.com.cleancode.usecases.animal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalRequest {
    public Long id;
    public String tag;
    public Long farm;
}
