package de.neuland_bfi.blueprints.ddd.domain.model.warenkorb;

import de.neuland_bfi.blueprints.ddd.domain.model.ValueObject;

@ValueObject
public class WarenkorbId {

    private final String value;

    public WarenkorbId(String value) {
        if (value == null || value.isEmpty())
            throw new IllegalArgumentException("'value' must not be empty");
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarenkorbId that = (WarenkorbId) o;

        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
