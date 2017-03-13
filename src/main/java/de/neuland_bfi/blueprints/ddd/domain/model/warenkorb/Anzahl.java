package de.neuland_bfi.blueprints.ddd.domain.model.warenkorb;

import de.neuland_bfi.blueprints.ddd.domain.model.ValueObject;

@ValueObject
public class Anzahl {


    private int value;

    public Anzahl(String value) {
        if (value == null)
            throw new IllegalArgumentException("'value' must not be 0");
        this.value = Integer.valueOf(value);
    }

    public int value() {
        return value;
    }

}
