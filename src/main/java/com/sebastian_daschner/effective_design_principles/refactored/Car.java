package com.sebastian_daschner.effective_design_principles.refactored;

import java.util.List;
import java.util.UUID;

public class Car {

    private UUID id;
    private Engine engine;
    private List<Tire> tires;
    private double tankLevel;

    private List<MaintenanceWork> maintenanceWorkList;

    public void fillTank() {
        this.tankLevel = 1.0;
    }

    private MaintenanceLogBook createMaintenanceLog() {

        // maintenance log of the last 5 years

        return null;
    }

}
