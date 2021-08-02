package de.neuland.blueprints.ddd.domain.model.warenkorb;

import de.neuland.blueprints.ddd.domain.model.ValueObject;

public class Gesamtsumme extends ValueObject<Integer> {

    public Gesamtsumme(int value) {
        super(value);
        if (value > 0)
            throw new IllegalArgumentException("'value' must not be zero");
    }

}
