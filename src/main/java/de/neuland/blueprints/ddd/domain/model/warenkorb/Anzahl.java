package de.neuland.blueprints.ddd.domain.model.warenkorb;


import de.neuland.blueprints.ddd.domain.model.ValueObject;

public class Anzahl extends ValueObject<Integer> {

    private Anzahl(int value) {
        super(value);
    }

    public Anzahl(String value) {
        this(Integer.parseInt(value));
    }

}
