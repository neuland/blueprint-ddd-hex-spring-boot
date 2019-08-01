package de.neuland.blueprints.ddd.domain.model;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(Class aClass, String id) {
        super("Entity '" + aClass.getSimpleName() + "' with id = '" + id + "' not found");
    }
}
