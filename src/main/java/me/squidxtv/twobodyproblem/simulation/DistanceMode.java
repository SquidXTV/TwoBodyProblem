package me.squidxtv.twobodyproblem.simulation;


/**
 * How much distance is in 1000px
 */
public enum DistanceMode {
    KILOMETER(1000),
    LUNAR(3.84399e8),
    TWO_LUNAR(LUNAR.inMeter*2),
    ASTRONOMICAL_UNIT(1.495978707e11),
    TWO_ASTRONOMICAL_UNITS(ASTRONOMICAL_UNIT.inMeter*2),
    LIGHT_YEAR(9.4607e15),
    PARSEC(3.0857e16);

    private final double inMeter;

    DistanceMode(double inMeter) {
        this.inMeter = inMeter;
    }

    public double getInMeter() {
        return inMeter;
    }
}
