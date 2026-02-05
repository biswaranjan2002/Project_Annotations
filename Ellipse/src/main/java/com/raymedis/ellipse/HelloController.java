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

    private final List<AngleAnnotation> angles = new ArrayList<>();

    private final List<DistanceAnnotation> distances = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();

        loadEllipses();
        loadAngles();
        loadRectangles();
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

    private void redrawCanvas() {
        gc.clearRect(9, 9, canvas.getWidth(), canvas.getHeight());


        for (EllipseAnnotation ellipse : ellipses) {
            drawEllipse(gc, ellipse);
        }

        for (AngleAnnotation angle : angles) {
            drawAngle(gc, angle);
        }

        for (RectangleAnnotation r : rectangles) {
            drawRectangle(gc, r);
        }

        for (DistanceAnnotation d : distances) {
            drawDistance(gc, d);
        }
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

    private void loadAngles() {

        // Center (vertex)
        PointAnnotation center = new PointAnnotation(300, 300);
        center.setRadius(6);
        center.setFillColor("#FFFF00"); // yellow

        // Left arm point
        PointAnnotation left = new PointAnnotation(200, 300);
        left.setRadius(4);
        left.setFillColor("#FF0000"); // red

        // Right arm point
        PointAnnotation right = new PointAnnotation(350, 200);
        right.setRadius(4);
        right.setFillColor("#0000FF"); // blue

        // Create angle annotation (auto-calculates angle)
        AngleAnnotation angle = new AngleAnnotation(left, center, right);

        angles.add(angle);
    }


    private void drawAngle(GraphicsContext gc, AngleAnnotation angle) {

        // draw lines
        drawAngleLine(gc, angle.getCenterVertex(), angle.getLeftVertex(),
                angle.getLineStrokeWidth(), angle.getLineStrokeColor());

        drawAngleLine(gc, angle.getCenterVertex(), angle.getRightVertex(),
                angle.getLineStrokeWidth(), angle.getLineStrokeColor());

        // draw points
        drawHighlightedPoint(gc, angle.getCenterVertex());
        drawHighlightedPoint(gc, angle.getLeftVertex());
        drawHighlightedPoint(gc, angle.getRightVertex());

        // 👇 THIS is the key line
        drawAngleLabel(gc, angle.getLabel(), angle.getCenterVertex());
    }


    private void drawAngleLine(
            GraphicsContext gc,
            PointAnnotation from,
            PointAnnotation to,
            double width,
            String color
    ) {
        gc.setStroke(Color.web(color));
        gc.setLineWidth(width);
        gc.strokeLine(
                from.getX(),
                from.getY(),
                to.getX(),
                to.getY()
        );
    }

    private void drawAngleLabel(
            GraphicsContext gc,
            LabelAnnotation label,
            PointAnnotation center
    ) {
        if (label == null) return;

        // controller decides position
        double offsetX = 20;
        double offsetY = -20;

        label.setPosition(
                new PointAnnotation(
                        center.getX() + offsetX,
                        center.getY() + offsetY
                )
        );

        drawLabel(gc, label);
    }

    private void drawLabel(GraphicsContext gc, LabelAnnotation label) {

        if (label == null || label.getPosition() == null) return;

        String text = String.format(
                "%.2f %s",
                label.getValue(),
                label.getUnit()
        );

        gc.setFill(Color.WHITE);
        gc.fillText(
                text,
                label.getPosition().getX(),
                label.getPosition().getY()
        );
    }

    private void drawHighlightedPoint(GraphicsContext gc, PointAnnotation p) {

        if (p == null) return;

        double r = p.getRadius();

        // fill (inside)
        gc.setFill(Color.YELLOW);
        gc.fillOval(
                p.getX() - r,
                p.getY() - r,
                r * 2,
                r * 2
        );

        // border (highlight)
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
        gc.strokeOval(
                p.getX() - r,
                p.getY() - r,
                r * 2,
                r * 2
        );
    }
    private void loadRectangles() {

        // Rectangle 1 points
        PointAnnotation tl1 = new PointAnnotation(100, 100);
        PointAnnotation tr1 = new PointAnnotation(300, 100);
        PointAnnotation br1 = new PointAnnotation(300, 220);
        PointAnnotation bl1 = new PointAnnotation(100, 220);

        RectangleAnnotation r1 = new RectangleAnnotation(
                tl1,
                tr1,
                br1,
                bl1,
                2.0,            // stroke width
                "#FF0000"       // color
        );

        // Rectangle 2 points
        PointAnnotation tl2 = new PointAnnotation(350, 200);
        PointAnnotation tr2 = new PointAnnotation(500, 200);
        PointAnnotation br2 = new PointAnnotation(500, 380);
        PointAnnotation bl2 = new PointAnnotation(350, 380);

        RectangleAnnotation r2 = new RectangleAnnotation(
                tl2,
                tr2,
                br2,
                bl2,
                3.0,            // stroke width
                "#0000FF"       // color
        );

        rectangles.add(r1);
        rectangles.add(r2);
    }


    private void drawRectangle(GraphicsContext gc, RectangleAnnotation rect) {

        if (rect == null) return;

        drawLine(gc, rect.getTopEdge());
        drawLine(gc, rect.getRightEdge());
        drawLine(gc, rect.getBottomEdge());
        drawLine(gc, rect.getLeftEdge());
    }

    private void drawLine(GraphicsContext gc, LineAnnotation line) {

        if (line == null) return;

        gc.setStroke(Color.web(line.getStrokeColor()));
        gc.setLineWidth(line.getStrokeWidth());

        gc.strokeLine(
                line.getStartPoint().getX(),
                line.getStartPoint().getY(),
                line.getEndPoint().getX(),
                line.getEndPoint().getY()
        );
    }

    private void loadDistances() {

        PointAnnotation p1 = new PointAnnotation(100, 400);
        p1.setRadius(5);

        PointAnnotation p2 = new PointAnnotation(350, 450);
        p2.setRadius(5);

        DistanceAnnotation d1 = new DistanceAnnotation(
                p1,
                p2,
                "#00FF00",   // line color
                2.0,        // line width
                5.0         // point radius
        );

        distances.add(d1);
    }

    private void drawDistance(GraphicsContext gc, DistanceAnnotation d) {

        if (d == null) return;

        // 1️⃣ draw distance line
        drawLine(gc, d.getLine());

        // 2️⃣ draw start & end points
        drawHighlightedPoint(gc, d.getStartPoint());
        drawHighlightedPoint(gc, d.getEndPoint());

        // 3️⃣ draw label
        drawDistanceLabel(gc, d);
    }

    private void drawDistanceLabel(GraphicsContext gc, DistanceAnnotation d) {

        LabelAnnotation label = d.getDistanceLabel();
        if (label == null) return;

        PointAnnotation start = d.getStartPoint();
        PointAnnotation end = d.getEndPoint();

        // midpoint
        double midX = (start.getX() + end.getX()) / 2.0;
        double midY = (start.getY() + end.getY()) / 2.0;

        label.setPosition(new PointAnnotation(midX, midY));

        drawLabel(gc, label);
    }




}