package com.raymedis.ellipse.AnnotationsWrapper;

public class AngleAnnotation {

    private PointAnnotation leftVertex;
    private PointAnnotation centerVertex;
    private PointAnnotation rightVertex;
    private double lineStrokeWidth;
    private String lineStrokeColor;
    private LabelAnnotation label;

    public AngleAnnotation(PointAnnotation leftVertex, PointAnnotation centerVertex, PointAnnotation rightVertex, double lineStrokeWidth, String lineStrokeColor) {
        this.leftVertex = leftVertex;
        this.centerVertex = centerVertex;
        this.rightVertex = rightVertex;
        this.lineStrokeWidth = lineStrokeWidth;
        this.lineStrokeColor = lineStrokeColor;
        this.label = new LabelAnnotation("°");

        calculateAngle();
    }

    public AngleAnnotation(PointAnnotation leftVertex, PointAnnotation centerVertex, PointAnnotation rightVertex) {
        this.leftVertex = leftVertex;
        this.centerVertex = centerVertex;
        this.rightVertex = rightVertex;
        this.lineStrokeWidth=2.0;
        this.lineStrokeColor="#FF0000";
        this.label = new LabelAnnotation("°");
        calculateAngle();
    }

    public LabelAnnotation getLabel() {
        return label;
    }

    public void setLabel(LabelAnnotation label) {
        this.label = label;
    }

    public AngleAnnotation() {
    }

    public PointAnnotation getLeftVertex() {
        return leftVertex;
    }

    public void setLeftVertex(PointAnnotation leftVertex) {
        this.leftVertex = leftVertex;
    }

    public PointAnnotation getCenterVertex() {
        return centerVertex;
    }

    public void setCenterVertex(PointAnnotation centerVertex) {
        this.centerVertex = centerVertex;
    }

    public PointAnnotation getRightVertex() {
        return rightVertex;
    }

    public void setRightVertex(PointAnnotation rightVertex) {
        this.rightVertex = rightVertex;
    }

    public double getLineStrokeWidth() {
        return lineStrokeWidth;
    }

    public void setLineStrokeWidth(double lineStrokeWidth) {
        this.lineStrokeWidth = lineStrokeWidth;
    }

    public String getLineStrokeColor() {
        return lineStrokeColor;
    }

    public void setLineStrokeColor(String lineStrokeColor) {
        this.lineStrokeColor = lineStrokeColor;
    }

// Function for calculating angle of from the given line points || round to two decimal places
    public double calculateAngle() {

        if (leftVertex == null || centerVertex == null || rightVertex == null) {
            return 0.0;
        }

        double v1x = leftVertex.getX() - centerVertex.getX();
        double v1y = leftVertex.getY() - centerVertex.getY();

        double v2x = rightVertex.getX() - centerVertex.getX();
        double v2y = rightVertex.getY() - centerVertex.getY();

        double dot = (v1x * v2x) + (v1y * v2y);

        double mag1 = Math.sqrt(v1x * v1x + v1y * v1y);
        double mag2 = Math.sqrt(v2x * v2x + v2y * v2y);

        if (mag1 == 0 || mag2 == 0) {
            return 0.0;
        }

        double cosTheta = dot / (mag1 * mag2);

        cosTheta = Math.max(-1.0, Math.min(1.0, cosTheta));

        double angle = Math.toDegrees(Math.acos(cosTheta));

        if (label != null) {
            label.setValue(angle);
        }

        return angle;
    }

    @Override
    public String toString() {
        return "AngleAnnotation{" +
                "leftVertex=" + leftVertex +
                ", centerVertex=" + centerVertex +
                ", rightVertex=" + rightVertex +
                ", lineStrokeWidth=" + lineStrokeWidth +
                ", lineStrokeColor='" + lineStrokeColor + '\'' +
                ", label=" + label +
                '}';
    }
}