package me.squidxtv.twobodyproblem.simulation;

/**
 * How much time passes in one second of simulation
 */
public enum TimeMode {

    SECOND(1, "in seconds"),
    MINUTE(60, "in minutes"),
    HOUR(3600, "in hours"),
    DAY(86_400, "in days"),
    MONTH(2_592_000, "in months"),
    YEAR(31_557_600, "in years");

    private final int timeInSeconds;
    private final String title;

    TimeMode(int timeInSeconds,  String title) {
        this.timeInSeconds = timeInSeconds;
        this.title = " | " + title;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public String getTitle() {
        return title;
    }

}
