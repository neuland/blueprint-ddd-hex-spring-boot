package de.neuland.blueprints.ddd.domain.model.warenkorb;

import de.neuland.blueprints.ddd.domain.model.ValueObject;

public class ArtikelId extends ValueObject<String> {

    public ArtikelId(String value) {
        super(value);
        if (value.isEmpty())
            throw new IllegalArgumentException("'value' must not be empty");
    }

}
