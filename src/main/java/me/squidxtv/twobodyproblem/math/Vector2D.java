package me.squidxtv.twobodyproblem.math;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D other) {
        x += other.x;
        y += other.y;
        return this;
    }

    public Vector2D multiply(double scalar) {
        x *= scalar;
        y *= scalar;
        return this;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public double magnitudeSquared() {
        return x * x + y * y;
    }

    public Vector2D setMagnitude(double length) {
        normalize();
        multiply(length);
        return this;
    }

    public Vector2D normalize() {
        double mag = magnitude();
        if (mag == 0.0) {
            this.x = 0.0;
            this.y = 0.0;
            return this;
        }
        this.x /= mag;
        this.y /= mag;
        return this;
    }

    public Vector2D divide(double scalar) {
        return multiply(1.0 / scalar);
    }

    public static Vector2D subtract(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }

    public static Vector2D of(double x, double y) {
        return new Vector2D(x, y);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
