package me.squidxtv.twobodyproblem.simulation;

import javafx.scene.paint.Color;

public enum Preset {
    NONE(
            "",
            "",
            "",
            "",
            "",
            Color.WHITE,
            "",
            "",
            "",
            "",
            "",
            Color.WHITE,
            DistanceMode.ASTRONOMICAL_UNIT,
            TimeMode.SECOND,
            false),
    SUN_EARTH(
            "1.495978707e11",
            "1.495978707e11",
            "0",
            "0",
            "1.989e30",
            Color.RED,
            "0",
            "1.495978707e11",
            "0",
            "30e3",
            "5.972e24",
            Color.BLUE,
            DistanceMode.TWO_ASTRONOMICAL_UNITS,
            TimeMode.DAY,
            false
    );

    private final String firstX;
    private final String firstY;
    private final String firstVelX;
    private final String firstVelY;
    private final String firstMass;
    private final Color firstColor;

    private final String secondX;
    private final String secondY;
    private final String secondVelX;
    private final String secondVelY;
    private final String secondMass;
    private final Color secondColor;

    private final DistanceMode distanceMode;
    private final TimeMode timeMode;

    private final boolean isSlowed;


    Preset(String firstX, String firstY, String firstVelX, String firstVelY, String firstMass, Color firstColor, String secondX, String secondY, String secondVelX, String secondVelY, String secondMass, Color secondColor, DistanceMode distanceMode, TimeMode timeMode, boolean isSlowed) {
        this.firstX = firstX;
        this.firstY = firstY;
        this.firstVelX = firstVelX;
        this.firstVelY = firstVelY;
        this.firstMass = firstMass;
        this.firstColor = firstColor;
        this.secondX = secondX;
        this.secondY = secondY;
        this.secondVelX = secondVelX;
        this.secondVelY = secondVelY;
        this.secondMass = secondMass;
        this.secondColor = secondColor;
        this.distanceMode = distanceMode;
        this.timeMode = timeMode;
        this.isSlowed = isSlowed;
    }

    public String getFirstX() {
        return firstX;
    }

    public String getFirstY() {
        return firstY;
    }

    public String getFirstVelX() {
        return firstVelX;
    }

    public String getFirstVelY() {
        return firstVelY;
    }

    public String getFirstMass() {
        return firstMass;
    }

    public Color getFirstColor() {
        return firstColor;
    }

    public String getSecondX() {
        return secondX;
    }

    public String getSecondY() {
        return secondY;
    }

    public String getSecondVelX() {
        return secondVelX;
    }

    public String getSecondVelY() {
        return secondVelY;
    }

    public String getSecondMass() {
        return secondMass;
    }

    public Color getSecondColor() {
        return secondColor;
    }

    public DistanceMode getDistanceMode() {
        return distanceMode;
    }

    public TimeMode getTimeMode() {
        return timeMode;
    }

    public boolean isSlowed() {
        return isSlowed;
    }
}
