package me.squidxtv.twobody;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class TwoBody extends PApplet {

    private final List<Body> bodies = new ArrayList<>();

    @Override
    public void settings() {
        size(1000, 1000);
    }

    @Override
    public void setup() {
        background(0);
        ellipseMode(RADIUS);
        bodies.add(new Body(new PVector(width/3f, 500, 0), new PVector(0, -1), 10));
        bodies.add(new Body(new PVector(width/3f*2, 500, 0), new PVector(0, 1), 10));
        bodies.add(new Body(new PVector(0, height/2f, 0), new PVector(1, 0), 1));
    }

    @Override
    public void draw() {
        background(0, 20);
        for (Body body : bodies) {
            for (Body other : bodies) {
                if (body == other) {
                    continue;
                }
                body.attract(other);
            }
        }

        for (Body body : bodies) {
            body.update();
            body.draw(this);
        }
    }

}
