package com.raymedis.ellipse.AnnotationsWrapper;

public class AngleAnnotation {

    private PointAnnotation vertex;
    private LineAnnotation firstLine;
    private LineAnnotation secondLine;

    public AngleAnnotation() {
    }

    public AngleAnnotation(
            PointAnnotation vertex,
            LineAnnotation firstLine,
            LineAnnotation secondLine
    ) {
        this.vertex = vertex;
        this.firstLine = firstLine;
        this.secondLine = secondLine;
    }

    public PointAnnotation getVertex() {
        return vertex;
    }

    public void setVertex(PointAnnotation vertex) {
        this.vertex = vertex;
    }

    public LineAnnotation getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(LineAnnotation firstLine) {
        this.firstLine = firstLine;
    }

    public LineAnnotation getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(LineAnnotation secondLine) {
        this.secondLine = secondLine;
    }

    public double getAngleInDegrees() {

        double v1x = firstLine.getEndPoint().getX() - vertex.getX();
        double v1y = firstLine.getEndPoint().getY() - vertex.getY();

        double v2x = secondLine.getEndPoint().getX() - vertex.getX();
        double v2y = secondLine.getEndPoint().getY() - vertex.getY();

        double dot = v1x * v2x + v1y * v2y;
        double mag1 = Math.sqrt(v1x * v1x + v1y * v1y);
        double mag2 = Math.sqrt(v2x * v2x + v2y * v2y);

        if (mag1 == 0 || mag2 == 0) {
            return 0;
        }

        double cosTheta = dot / (mag1 * mag2);
        cosTheta = Math.max(-1, Math.min(1, cosTheta)); // safety

        return Math.toDegrees(Math.acos(cosTheta));
    }

    @Override
    public String toString() {
        return "AngleAnnotation{" +
                "vertex=" + vertex +
                ", angle=" + getAngleInDegrees() +
                '}';
    }
}