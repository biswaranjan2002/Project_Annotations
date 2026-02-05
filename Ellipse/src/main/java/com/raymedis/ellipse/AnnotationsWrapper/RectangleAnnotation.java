package com.raymedis.ellipse.AnnotationsWrapper;

public class RectangleAnnotation {

    private PointAnnotation topLeft;
    private PointAnnotation topRight;
    private PointAnnotation bottomRight;
    private PointAnnotation bottomLeft;

    private LineAnnotation topEdge;
    private LineAnnotation rightEdge;
    private LineAnnotation bottomEdge;
    private LineAnnotation leftEdge;

    public double strokeWidth;
    public String color;

    public RectangleAnnotation(PointAnnotation topLeft,
                               PointAnnotation topRight,
                               PointAnnotation bottomRight,
                               PointAnnotation bottomLeft,
                               double width,
                               String color) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomRight = bottomRight;
        this.bottomLeft = bottomLeft;

        this.strokeWidth = width;
        this.color = color;
        buildEdges();
    }

    private void buildEdges() {
        topEdge = new LineAnnotation(topLeft, topRight,strokeWidth,color);
        rightEdge = new LineAnnotation(topRight, bottomRight,strokeWidth,color);
        bottomEdge = new LineAnnotation(bottomRight, bottomLeft,strokeWidth,color);
        leftEdge = new LineAnnotation(bottomLeft, topLeft,strokeWidth,color);
    }


    public LineAnnotation getTopEdge() {
        return topEdge;
    }

    public LineAnnotation getRightEdge() {
        return rightEdge;
    }

    public LineAnnotation getBottomEdge() {
        return bottomEdge;
    }

    public LineAnnotation getLeftEdge() {
        return leftEdge;
    }


    public double getWidth() {
        return topRight.getX() - topLeft.getX();
    }

    public double getHeight() {
        return bottomLeft.getY() - topLeft.getY();
    }

    @Override
    public String toString() {
        return "RectangleAnnotation{" +
                "topLeft=" + topLeft +
                ", topRight=" + topRight +
                ", bottomRight=" + bottomRight +
                ", bottomLeft=" + bottomLeft +
                '}';
    }
}