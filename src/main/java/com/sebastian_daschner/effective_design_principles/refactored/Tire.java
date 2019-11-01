package com.sebastian_daschner.effective_design_principles.refactored;

public class Tire {

    private final int width;
    private final SpeedRating speedRating;

    public Tire(int width, SpeedRating speedRating) {
        this.width = width;
        this.speedRating = speedRating;
    }

    public int getWidth() {
        return width;
    }

    public SpeedRating getSpeedRating() {
        return speedRating;
    }
}
