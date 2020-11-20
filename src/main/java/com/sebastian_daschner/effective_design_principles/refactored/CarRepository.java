package com.sebastian_daschner.effective_design_principles.refactored;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CarRepository {

    @PersistenceContext
    EntityManager entityManager;

    List<Car> retrieveCars() {
        return entityManager.createNamedQuery("select ...", Car.class).getResultList();
    }
}
