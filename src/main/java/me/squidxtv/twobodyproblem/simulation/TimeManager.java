package me.squidxtv.twobodyproblem.simulation;

import javafx.scene.control.Label;

public final class TimeManager {

    /**
     * +1 every second
     * in slowmode:
     * +0.1 every second
     */
    private final TimeMode mode;
    private double time;
    private long millis;

    private final Label label;
    private final boolean slowed;

    public TimeManager(Label label, TimeMode mode, boolean isSlowed) {
        this.label = label;
        this.mode = mode;
        this.slowed = isSlowed;
    }

    public void start() {
        time = 0;
        millis = System.currentTimeMillis();
    }

    public void refresh() {
        long current = System.currentTimeMillis();
        double dt = (current - millis)/1000.0; // in seconds
        if (slowed) {
            dt /= 10;
        }
        time += dt;
        millis = current;

        label.setText("%.2f %s".formatted(time, mode.getTitle()));
    }

}
