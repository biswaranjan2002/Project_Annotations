package com.raymedis.ellipse.AnnotationsWrapper;

public class EllipseAnnotation {

    private double centerX;
    private double centerY;
    private double horizontalRadius;
    private double verticalRadius;


    private double strokeWidth;
    private String strokeColor;

    private boolean filled;
    private String fillColor;



    public EllipseAnnotation() {
        // Default values
        this.strokeWidth = 1.0;
        this.strokeColor = "#00FF00";
        this.fillColor = "#00000000";
        this.filled = false;
    }

    public EllipseAnnotation(
            double centerX,
            double centerY,
            double horizontalRadius,
            double verticalRadius
    ) {
        this();
        this.centerX = centerX;
        this.centerY = centerY;
        this.horizontalRadius = horizontalRadius;
        this.verticalRadius = verticalRadius;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public double getHorizontalRadius() {
        return horizontalRadius;
    }

    public void setHorizontalRadius(double horizontalRadius) {
        this.horizontalRadius = horizontalRadius;
    }

    public double getVerticalRadius() {
        return verticalRadius;
    }

    public void setVerticalRadius(double verticalRadius) {
        this.verticalRadius = verticalRadius;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public String toString() {
        return "EllipseAnnotation{" +
                "centerX=" + centerX +
                ", centerY=" + centerY +
                ", horizontalRadius=" + horizontalRadius +
                ", verticalRadius=" + verticalRadius +
                ", strokeWidth=" + strokeWidth +
                ", strokeColor='" + strokeColor + '\'' +
                ", filled=" + filled +
                ", fillColor='" + fillColor + '\'' +
                '}';
    }
}

