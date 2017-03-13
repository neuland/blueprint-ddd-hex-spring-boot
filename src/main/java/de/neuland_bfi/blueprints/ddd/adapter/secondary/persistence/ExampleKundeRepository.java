package de.neuland_bfi.blueprints.ddd.adapter.secondary.persistence;

import de.neuland_bfi.blueprints.ddd.domain.model.kunde.Kunde;
import de.neuland_bfi.blueprints.ddd.domain.model.kunde.KundeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleKundeRepository implements KundeRepository {

    private final static Logger LOG = LoggerFactory.getLogger(ExampleWarenkorbRepository.class);

    @Override
    public void save(Kunde kunde) {
        LOG.info("Kunde angelegt {}", kunde.kundennummer().value());
    }
}
