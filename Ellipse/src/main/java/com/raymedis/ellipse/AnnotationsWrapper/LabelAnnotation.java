package com.raymedis.ellipse.AnnotationsWrapper;

public class LabelAnnotation {

    private double value;                 // value to display (angle, length, etc.)
    private PointAnnotation position;     // label position on canvas
    private String label;
    private String unit;

    public LabelAnnotation(double value, PointAnnotation position, String unit) {
        this.value = value;
        this.position = position;
        this.unit = unit;
    }
    public LabelAnnotation(PointAnnotation position, String label, String unit) {
        this.position = position;
        this.label = label;
        this.unit = unit;
    }

    public LabelAnnotation(String unit) {
        this.unit = unit;
    }


    @Override
    public String toString() {
        return "LabelAnnotation{" +
                "value=" + value +
                ", position=" + position +
                ", label='" + label + '\'' +
                ", unit='" + unit + '\'' +
                '}';
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
