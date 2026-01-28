package com.raymedis.ellipse;

import com.raymedis.ellipse.AnnotationsWrapper.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    private final List<EllipseAnnotation> ellipses = new ArrayList<>();

    private final List<RectangleAnnotation> rectangles = new ArrayList<>();

    private final List<LineAnnotation> lines = new ArrayList<>();

    private final List<PointAnnotation> points = new ArrayList<>();

    private final List<AngleAnnotation> angles = new ArrayList<>();

    private final List<DistanceAnnotation> distances = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();

        loadEllipses();
        loadRectangles();
        loadLines();
        loadPoints();
        loadAngles();
        loadDistances();
        redrawCanvas();
    }

    private void loadEllipses() {

        EllipseAnnotation e1 = new EllipseAnnotation(
                200, 150, 80, 80
        );
        e1.setStrokeColor("#FF0000");
        e1.setStrokeWidth(2);

        EllipseAnnotation e2 = new EllipseAnnotation(
                400, 300, 90, 90
        );
        e2.setStrokeColor("#0000FF");
        e2.setStrokeWidth(3);
        e2.setFilled(true);
        e2.setFillColor("#330000FF");

        ellipses.add(e1);
        ellipses.add(e2);
    }


    private void loadRectangles() {

        RectangleAnnotation r1 = new RectangleAnnotation(
                100, 100,   // x, y
                200, 120    // width, height
        );

        RectangleAnnotation r2 = new RectangleAnnotation(
                350, 200,
                150, 180
        );

        rectangles.add(r1);
        rectangles.add(r2);
    }

    private void loadLines() {

        LineAnnotation l1 = new LineAnnotation(
                new PointAnnotation(50, 50),
                new PointAnnotation(200, 80)
        );
        l1.setStrokeColor("#FF0000");
        l1.setStrokeWidth(2);

        LineAnnotation l2 = new LineAnnotation(
                300, 150,
                500, 300
        );
        l2.setStrokeColor("#0000FF");
        l2.setStrokeWidth(3);

        lines.add(l1);
        lines.add(l2);
    }

    private void loadPoints() {

        PointAnnotation p1 = new PointAnnotation(100, 100);
        p1.setRadius(4);
        p1.setFillColor("#FF0000");

        PointAnnotation p2 = new PointAnnotation(250, 180);
        p2.setRadius(6);
        p2.setFillColor("#0000FF");

        PointAnnotation p3 = new PointAnnotation(400, 300);
        p3.setRadius(5);
        p3.setFillColor("#00FF00");

        points.add(p1);
        points.add(p2);
        points.add(p3);
    }

    private void loadAngles() {

        PointAnnotation vertex = new PointAnnotation(300, 300);
        vertex.setRadius(6);
        vertex.setFillColor("#FFFF00"); // yellow vertex

        LineAnnotation l1 = new LineAnnotation(
                vertex,
                new PointAnnotation(400, 250)
        );
        l1.setStrokeColor("#FF0000");
        l1.setStrokeWidth(2);

        LineAnnotation l2 = new LineAnnotation(
                vertex,
                new PointAnnotation(350, 400)
        );
        l2.setStrokeColor("#0000FF");
        l2.setStrokeWidth(2);

        AngleAnnotation angle = new AngleAnnotation(vertex, l1, l2);

        angles.add(angle);
    }

    private void redrawCanvas() {
        gc.clearRect(9, 9, canvas.getWidth(), canvas.getHeight());

        for (PointAnnotation p : points) {
            drawPoint(gc, p);
        }

        for (EllipseAnnotation ellipse : ellipses) {
            drawEllipse(gc, ellipse);
        }

        for (RectangleAnnotation r : rectangles) {
            drawRectangle(gc, r);
        }

        for (LineAnnotation l : lines) {
            drawLine(gc, l);
        }

        for (AngleAnnotation a : angles) {
            drawAngle(gc, a);
        }

        for (DistanceAnnotation d : distances) drawDistance(gc, d);

    }

    private void drawEllipse(GraphicsContext gc, EllipseAnnotation e) {

        double x = e.getCenterX() - e.getHorizontalRadius();
        double y = e.getCenterY() - e.getVerticalRadius();
        double w = e.getHorizontalRadius() * 2;
        double h = e.getVerticalRadius() * 2;

        gc.setStroke(Color.web(e.getStrokeColor()));
        gc.setLineWidth(e.getStrokeWidth());
        gc.strokeOval(x, y, w, h);

        if (e.isFilled()) {
            gc.setFill(Color.web(e.getFillColor()));
            gc.fillOval(x, y, w, h);
        }
    }

    private void drawPoint(GraphicsContext gc, PointAnnotation p) {

        double r = p.getRadius();

        gc.setFill(Color.web(p.getFillColor()));
        gc.fillOval(
                p.getX() - r,
                p.getY() - r,
                r * 2,
                r * 2
        );
    }


    private void drawRectangle(GraphicsContext gc, RectangleAnnotation r) {
        drawLine(gc, r.getTopEdge());
        drawLine(gc, r.getRightEdge());
        drawLine(gc, r.getBottomEdge());
        drawLine(gc, r.getLeftEdge());
    }

    private void drawLine(GraphicsContext gc, LineAnnotation line) {

        gc.setStroke(Color.web(line.getStrokeColor()));
        gc.setLineWidth(line.getStrokeWidth());

        gc.strokeLine(
                line.getStartPoint().getX(),
                line.getStartPoint().getY(),
                line.getEndPoint().getX(),
                line.getEndPoint().getY()
        );
    }

    private void drawAngle(GraphicsContext gc, AngleAnnotation angle) {

        // draw lines
        drawLine(gc, angle.getFirstLine());
        drawLine(gc, angle.getSecondLine());

        // highlight points
        drawHighlightedPoint(gc, angle.getVertex());
        drawHighlightedPoint(gc, angle.getFirstLine().getEndPoint());
        drawHighlightedPoint(gc, angle.getSecondLine().getEndPoint());
    }

    private void drawHighlightedPoint(GraphicsContext gc, PointAnnotation p) {

        double r = p.getRadius();

        gc.setFill(Color.YELLOW);
        gc.fillOval(
                p.getX() - r,
                p.getY() - r,
                r * 2,
                r * 2
        );

        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        gc.strokeOval(
                p.getX() - r,
                p.getY() - r,
                r * 2,
                r * 2
        );
    }

    private void loadDistances() {

        DistanceAnnotation d1 = new DistanceAnnotation(
                new PointAnnotation(100, 100),
                new PointAnnotation(300, 150)
        );

        DistanceAnnotation d2 = new DistanceAnnotation(
                new PointAnnotation(200, 250),
                new PointAnnotation(450, 350)
        );

        distances.add(d1);
        distances.add(d2);
    }

    private void drawDistance(GraphicsContext gc, DistanceAnnotation d) {

        LineAnnotation line = createDistanceLine(d);
        LabelAnnotation label = createDistanceLabel(d);

        drawLine(gc, line);

        drawHighlightedPoint(gc, d.getStartPoint());
        drawHighlightedPoint(gc, d.getEndPoint());

        drawLabel(gc, label);
    }

    private LabelAnnotation createDistanceLabel(DistanceAnnotation d) {

        double midX =
                (d.getStartPoint().getX() + d.getEndPoint().getX()) / 2.0;
        double midY =
                (d.getStartPoint().getY() + d.getEndPoint().getY()) / 2.0;

        double distance = calculateDistance(d);

        return new LabelAnnotation(
                distance,
                new PointAnnotation(midX, midY)
        );
    }

    private LineAnnotation createDistanceLine(DistanceAnnotation d) {
        LineAnnotation line =
                new LineAnnotation(d.getStartPoint(), d.getEndPoint());

        line.setStrokeWidth(2);
        line.setStrokeColor("#00FFFF"); // example

        return line;
    }

    private double calculateDistance(DistanceAnnotation d) {
        double dx = d.getEndPoint().getX() - d.getStartPoint().getX();
        double dy = d.getEndPoint().getY() - d.getStartPoint().getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private void drawLabel(GraphicsContext gc, LabelAnnotation label) {

        if (label == null || label.getPosition() == null) {
            return;
        }

        PointAnnotation pos = label.getPosition();

        // text style
        gc.setFill(Color.WHITE);
        gc.setLineWidth(1);

        // optional background for readability
        String text = String.format("%.2f", label.getValue());

        gc.fillText(
                text,
                pos.getX(),
                pos.getY()
        );
    }

}