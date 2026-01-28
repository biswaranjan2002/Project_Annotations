package com.raymedis.ellipse.AnnotationsWrapper;

public class LabelAnnotation {

    private double value;                 // value to display (angle, length, etc.)
    private PointAnnotation position;     // label position on canvas

    public LabelAnnotation() {
    }

    public LabelAnnotation(double value, PointAnnotation position) {
        this.value = value;
        this.position = position;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public PointAnnotation getPosition() {
        return position;
    }

    public void setPosition(PointAnnotation position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "LabelAnnotation{" +
                "value=" + value +
                ", position=" + position +
                '}';
    }
}
