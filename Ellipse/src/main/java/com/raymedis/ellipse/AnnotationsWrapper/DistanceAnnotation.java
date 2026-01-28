package com.raymedis.ellipse.AnnotationsWrapper;

public class DistanceAnnotation {

    private PointAnnotation startPoint;
    private PointAnnotation endPoint;

    // Derived
    private LineAnnotation line;
    private LabelAnnotation distanceLabel;

    public DistanceAnnotation() {
    }

    public DistanceAnnotation(PointAnnotation startPoint, PointAnnotation endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public DistanceAnnotation(
            double startX, double startY,
            double endX, double endY
    ) {
        this(
                new PointAnnotation(startX, startY),
                new PointAnnotation(endX, endY)
        );
    }

    public PointAnnotation getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(PointAnnotation startPoint) {
        this.startPoint = startPoint;
    }

    public PointAnnotation getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(PointAnnotation endPoint) {
        this.endPoint = endPoint;
    }

    public LineAnnotation getLine() {
        return line;
    }

    public LabelAnnotation getDistanceLabel() {
        return distanceLabel;
    }

    @Override
    public String toString() {
        return "DistanceAnnotation{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }
}