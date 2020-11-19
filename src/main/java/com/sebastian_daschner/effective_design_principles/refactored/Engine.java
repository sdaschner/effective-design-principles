package com.sebastian_daschner.effective_design_principles.refactored;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Engine {

    @Id
    private UUID id;

}
