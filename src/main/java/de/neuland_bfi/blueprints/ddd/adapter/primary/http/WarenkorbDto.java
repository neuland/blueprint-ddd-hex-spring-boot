package de.neuland_bfi.blueprints.ddd.adapter.primary.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
class WarenkorbDto {

    private String warenkorbId;
    private List<PositionDto> positionen;

}
