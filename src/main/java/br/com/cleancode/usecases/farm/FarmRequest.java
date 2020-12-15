package br.com.cleancode.usecases.farm;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FarmRequest {
    private Long id;
    private String name;
}
