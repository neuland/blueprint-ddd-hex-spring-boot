package de.neuland.blueprints.ddd.adapter.secondary.persistence;

import de.neuland.blueprints.ddd.domain.model.EntityNotFoundException;
import de.neuland.blueprints.ddd.domain.model.warenkorb.Warenkorb;
import de.neuland.blueprints.ddd.domain.model.warenkorb.WarenkorbId;
import de.neuland.blueprints.ddd.domain.model.warenkorb.WarenkorbRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
class ExampleWarenkorbRepository implements WarenkorbRepository {

    private final Map<WarenkorbId, Warenkorb> warenkoerbe = new HashMap<>();

    @Override
    public void save(Warenkorb warenkorb) {
        warenkoerbe.put(warenkorb.warenkorbId(), warenkorb);
        log.info("Warenkorb gespeichert");
    }

    @Override
    public Warenkorb find(WarenkorbId warenkorbId) throws EntityNotFoundException {
        return findAsOption(warenkorbId).orElseThrow(() -> new EntityNotFoundException(Warenkorb.class, warenkorbId.getValue()));
    }

    @Override
    public Optional<Warenkorb> findAsOption(WarenkorbId warenkorbId) {
        var warenkorb = warenkoerbe.getOrDefault(warenkorbId, null);
        if (warenkorb != null)
            log.info("Warenkorb gefunden");
        return Optional.ofNullable(warenkorb);
    }
}
