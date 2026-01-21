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

    public RectangleAnnotation(PointAnnotation topLeft,
                               PointAnnotation topRight,
                               PointAnnotation bottomRight,
                               PointAnnotation bottomLeft) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomRight = bottomRight;
        this.bottomLeft = bottomLeft;
        buildEdges();
    }

    public RectangleAnnotation(double x, double y, double width, double height) {
        this(
                new PointAnnotation(x, y),
                new PointAnnotation(x + width, y),
                new PointAnnotation(x + width, y + height),
                new PointAnnotation(x, y + height)
        );
    }

    private void buildEdges() {
        topEdge = new LineAnnotation(topLeft, topRight);
        rightEdge = new LineAnnotation(topRight, bottomRight);
        bottomEdge = new LineAnnotation(bottomRight, bottomLeft);
        leftEdge = new LineAnnotation(bottomLeft, topLeft);
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