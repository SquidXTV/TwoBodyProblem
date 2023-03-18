package me.squidxtv.twobodyproblem.simulation;


public record SimulationLoop(Body first, Body second, double timeStep) implements Runnable {

    @Override
    public void run() {
        first.attract(second, timeStep);
        second.attract(first, timeStep);
    }

}
