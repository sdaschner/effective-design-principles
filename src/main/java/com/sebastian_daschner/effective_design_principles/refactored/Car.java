package com.sebastian_daschner.effective_design_principles.refactored;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.UUID;

@Entity
public class Car {

    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private Engine engine = new Engine();

    private List<Tire> tires;
    private double tankLevel;
    private List<MaintenanceWork> maintenanceWorkList;

    public void fillTank() {
        this.tankLevel = 1.0;
    }

    public MaintenanceLogBook createMaintenanceLog() {

        // maintenance log of the last 5 years

        return null;
    }

}
