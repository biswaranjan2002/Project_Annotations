package com.raymedis.ellipse.AnnotationsWrapper;

public class LineAnnotation {

    private PointAnnotation startPoint;
    private PointAnnotation endPoint;

    private double strokeWidth;
    private String strokeColor;


    public LineAnnotation() {
        this.strokeWidth = 1.0;
        this.strokeColor = "#00FF00";
    }

    public LineAnnotation(PointAnnotation startPoint, PointAnnotation endPoint) {
        this();
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public LineAnnotation(
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

    @Override
    public String toString() {
        return "LineAnnotation{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", strokeWidth=" + strokeWidth +
                ", strokeColor='" + strokeColor + '\'' +
                '}';
    }
}