package com.sebastian_daschner.effective_design_principles.refactored;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class CarService {

    @PersistenceContext
    EntityManager entityManager;

    void createCar() {
        // ...
        entityManager.merge(new Car());
    }

}
