package com.sebastian_daschner.effective_design_principles.refactored;

import javax.persistence.Embeddable;

@Embeddable
public class Tire {

    private int width;
    private SpeedRating speedRating;

    public Tire(int width, SpeedRating speedRating) {
        this.width = width;
        this.speedRating = speedRating;
    }

    private Tire() {
    }

    public int getWidth() {
        return width;
    }

    public SpeedRating getSpeedRating() {
        return speedRating;
    }
}
