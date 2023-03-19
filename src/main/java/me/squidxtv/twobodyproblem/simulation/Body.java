package me.squidxtv.twobodyproblem.simulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.squidxtv.twobodyproblem.App;
import me.squidxtv.twobodyproblem.math.Vector2D;

public class Body {

    private static final double G = 6.67430e-11; // in m^3/(kg*s^2)

    private final DistanceMode distanceMode;
    private final Vector2D position; // in m
    private final Vector2D velocity; // in m/s

    private final double mass; // in kg

    private final Color color;

    private final double diameter; // in px

    public Body(Vector2D position, Vector2D velocity, double mass, Color color, DistanceMode distanceMode) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        this.color = color;
        this.diameter = 5;
        this.distanceMode = distanceMode;
    }

    public void draw(GraphicsContext graphics) {
        graphics.setFill(color);
        graphics.setStroke(color);

        double inMeter = distanceMode.getInMeter();
        double topLeftX = position.getX()/inMeter * App.WIDTH;
        topLeftX -= (diameter/2);
        double topLeftY = position.getY()/inMeter * App.HEIGHT;
        topLeftY -= (diameter/2.0);
        graphics.fillOval(topLeftX, topLeftY, diameter, diameter);
    }

    public void attract(Body other, double timeStep) {
        Vector2D force = Vector2D.subtract(other.position, position);
        double fg = G * mass * other.mass;
        double magnitudeSq = force.magnitudeSquared();
        fg /= magnitudeSq;
        force.setMagnitude(fg);

        force = force.divide(mass).multiply(timeStep);
        velocity.add(force);
        position.add(Vector2D.multiply(velocity, timeStep));
    }

    @Override
    public String toString() {
        return "Body{" +
                "distanceMode=" + distanceMode +
                ", position=" + position +
                ", velocity=" + velocity +
                ", mass=" + mass +
                ", color=" + color +
                '}';
    }
}
