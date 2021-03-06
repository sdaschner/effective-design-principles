package com.sebastian_daschner.effective_design_principles.refactored;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CarService {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    CarRepository repository;

    void createCar() {
        // ...
        entityManager.merge(new Car());
    }

    List<Car> retrieveCars() {
        return entityManager.createNamedQuery("select ...", Car.class).getResultList();
    }

}
