package de.neuland.blueprints.ddd.domain.model.warenkorb;

import de.neuland.blueprints.ddd.domain.model.EntityNotFoundException;

import java.util.Optional;

public interface WarenkorbRepository {

    void save(Warenkorb warenkorb);

    Warenkorb find(WarenkorbId warenkorbId) throws EntityNotFoundException;

    Optional<Warenkorb> findAsOption(WarenkorbId warenkorbId);

}
