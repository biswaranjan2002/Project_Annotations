package com.raymedis.ellipse.AnnotationsWrapper;

public class PointAnnotation {

    private double x;
    private double y;


    private double radius;

    private String fillColor;

    public PointAnnotation() {
        this.radius = 3.0;
        this.fillColor = "#00FF00";
    }

    public PointAnnotation(double x, double y) {
        this();
        this.x = x;
        this.y = y;
    }

    public PointAnnotation(double x, double y, double radius) {
        this(x, y);
        this.radius = radius;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public String toString() {
        return "PointAnnotation{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                ", fillColor='" + fillColor + '\'' +
                '}';
    }
}