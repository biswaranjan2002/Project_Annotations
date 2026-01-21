package com.raymedis.ellipse;

import com.raymedis.ellipse.AnnotationsWrapper.EllipseAnnotation;
import com.raymedis.ellipse.AnnotationsWrapper.LineAnnotation;
import com.raymedis.ellipse.AnnotationsWrapper.PointAnnotation;
import com.raymedis.ellipse.AnnotationsWrapper.RectangleAnnotation;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();

        loadEllipses();
        loadRectangles();
        loadLines();
        loadPoints();
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

}
