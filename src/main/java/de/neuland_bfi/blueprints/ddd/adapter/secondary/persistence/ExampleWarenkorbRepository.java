package de.neuland_bfi.blueprints.ddd.adapter.secondary.persistence;

import de.neuland_bfi.blueprints.ddd.domain.model.EntityNotFoundException;
import de.neuland_bfi.blueprints.ddd.domain.model.warenkorb.Warenkorb;
import de.neuland_bfi.blueprints.ddd.domain.model.warenkorb.WarenkorbId;
import de.neuland_bfi.blueprints.ddd.domain.model.warenkorb.WarenkorbRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
class ExampleWarenkorbRepository implements WarenkorbRepository {

    private final static Logger LOG = LoggerFactory.getLogger(ExampleWarenkorbRepository.class);

    private Map<WarenkorbId, Warenkorb> warenkörbe = new HashMap<>();

    @Override
    public void save(Warenkorb warenkorb) {
        warenkörbe.put(warenkorb.warenkorbId(), warenkorb);
        LOG.info("Warenkorb gespeichert");
    }

    @Override
    public Warenkorb find(WarenkorbId warenkorbId) throws EntityNotFoundException {
        return findAsOption(warenkorbId).orElseThrow(() -> new EntityNotFoundException(Warenkorb.class, warenkorbId.value()));
    }

    @Override
    public Optional<Warenkorb> findAsOption(WarenkorbId warenkorbId) {
        final Warenkorb warenkorb = warenkörbe.getOrDefault(warenkorbId, null);
        if (warenkorb != null)
            LOG.info("Warenkorb gefunden");
        return Optional.ofNullable(warenkorb);
    }
}
