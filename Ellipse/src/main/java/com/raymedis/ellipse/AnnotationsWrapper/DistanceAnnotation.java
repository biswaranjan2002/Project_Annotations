package com.raymedis.ellipse.AnnotationsWrapper;

public class DistanceAnnotation {

    private PointAnnotation startPoint;
    private PointAnnotation endPoint;

    // Derived
    private LineAnnotation line;
    private LabelAnnotation distanceLabel;
    String fillColor;
    double lineWidth;
    double radius;

    public DistanceAnnotation() {
    }

    public DistanceAnnotation(PointAnnotation startPoint, PointAnnotation endPoint, String fillColor, double lineWidth, double radius) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.fillColor = fillColor;
        this.lineWidth = lineWidth;
        this.radius = radius;

        this.line = new LineAnnotation(startPoint, endPoint, lineWidth,fillColor);
        this.distanceLabel = new LabelAnnotation("cm");

        calculateDistance();
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

    public void setLine(LineAnnotation line) {
        this.line = line;
    }

    public LabelAnnotation getDistanceLabel() {
        return distanceLabel;
    }

    public void setDistanceLabel(LabelAnnotation distanceLabel) {
        this.distanceLabel = distanceLabel;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    private static final double PIXEL_TO_CM = 2.54 / 96.0;

    public double calculateDistance() {

        if (startPoint == null || endPoint == null) {
            return 0.0;
        }

        double dx = endPoint.getX() - startPoint.getX();
        double dy = endPoint.getY() - startPoint.getY();

        double distanceInPixels = Math.sqrt(dx * dx + dy * dy);
        double distanceInCm = distanceInPixels * PIXEL_TO_CM;

        // 👇 value SET ONLY HERE
        if (distanceLabel != null) {
            distanceLabel.setValue(distanceInCm);
        }

        return distanceInCm;
    }



    @Override
    public String toString() {
        return "DistanceAnnotation{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", distanceLabel=" + distanceLabel +
                ", fillColor='" + fillColor + '\'' +
                ", lineWidth=" + lineWidth +
                ", radius=" + radius +
                '}';
    }
}