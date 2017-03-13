package de.neuland_bfi.blueprints.ddd.domain.model.warenkorb;

import de.neuland_bfi.blueprints.ddd.domain.model.ValueObject;

@ValueObject
public class ArtikelId {

    private String value;

    public ArtikelId(String value) {
        if (value == null || value.isEmpty())
            throw new IllegalArgumentException("'value' must not be empty");
        this.value = value;
    }

    public String value() {
        return value;
    }
}
