package me.squidxtv.twobody;

import processing.core.PApplet;
import processing.core.PVector;

public class Body {

    private static final float G = 1;
    private final PVector position;
    private final PVector velocity;

    private final PVector acceleration;

    private float mass;


    public Body(PVector position, PVector velocity, float mass) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new PVector(0, 0);
        this.mass = mass;
    }

    public void draw(PApplet parent) {
        parent.fill(255, 100);
        parent.stroke(255);
        parent.circle(position.x, position.y, PApplet.sqrt(mass) * 2 * 2);
    }

    public void attract(Body other) {
        PVector force = PVector.sub(position, other.position);
        float distanceSq = PApplet.constrain(force.magSq(), 100, 1000);
        force.normalize();
        
        float fg = -(G * (mass * other.mass)) / (distanceSq);
        force.setMag(fg);
        acceleration.add(PVector.div(force, mass));
    }

    public void update() {
        position.add(velocity.add(acceleration));
        acceleration.set(0, 0);
    }

    public void setMass(float mass) {
        this.mass = mass;
    }
}
