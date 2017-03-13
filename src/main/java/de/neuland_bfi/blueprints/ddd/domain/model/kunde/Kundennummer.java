package de.neuland_bfi.blueprints.ddd.domain.model.kunde;

import de.neuland_bfi.blueprints.ddd.domain.model.ValueObject;

@ValueObject
public class Kundennummer {

    private String value;

    public Kundennummer(String value) {
        if (value == null || value.isEmpty())
            throw new IllegalArgumentException("'value' must not be empty");
        this.value = value;
    }

    public String value() {
        return value;
    }

}
