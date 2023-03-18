package me.squidxtv.twobodyproblem.simulation;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Simulation extends AnimationTimer {

    private static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    private final GraphicsContext graphics;
    private final double width;
    private final double height;

    private boolean active = false;
    private ScheduledFuture<?> running = null;

    private TimeManager time = null;

    private Body first;
    private Body second;

    public Simulation(Canvas canvas) {
        this.graphics = canvas.getGraphicsContext2D();
        this.width = canvas.getWidth();
        this.height = canvas.getHeight();
        graphics.setFill(Color.BLACK);
        graphics.fillRect(-100, -100, width+100, height+100);
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException();
    }

    public void start(Body first, Body second, Label timeLabel, TimeMode timeMode, boolean isSlowed) {
        if (active) {
            stop();
        }
        super.start();
        active = true;
        graphics.setFill(Color.BLACK);
        graphics.fillRect(-100, -100, width+100, height+100);

        time = new TimeManager(timeLabel, timeMode, isSlowed);
        double timeStep = timeMode.getTimeInSeconds() / (isSlowed ? 10.0 : 1.0);
        SimulationLoop runnable = new SimulationLoop(first, second, timeStep);
        running = EXECUTOR.scheduleWithFixedDelay(runnable, 0, 1, TimeUnit.SECONDS);

        this.first = first;
        this.second = second;

        time.start();
    }

    @Override
    public void stop() {
        super.stop();
        if (!active) {
            return;
        }
        active = false;
        running.cancel(true);
        running = null;
        this.first = null;
        this.second = null;
    }

    @Override
    public void handle(long l) {
        time.refresh();

        first.draw(graphics);
        second.draw(graphics);
    }

}
