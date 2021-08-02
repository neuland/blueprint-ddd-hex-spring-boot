package de.neuland.blueprints.ddd.adapter.secondary.persistence;

import de.neuland.blueprints.ddd.domain.model.kunde.Kunde;
import de.neuland.blueprints.ddd.domain.model.kunde.KundeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ExampleKundeRepository implements KundeRepository {

    @Override
    public void save(Kunde kunde) {
        log.info("Kunde angelegt {}", kunde.kundennummer().getValue());
    }
}
