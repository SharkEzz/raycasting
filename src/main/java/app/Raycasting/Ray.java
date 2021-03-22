package app.Raycasting;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.Optional;

public class Ray {
    private final Point2D startPoints;
    private final Point2D direction;
    private final Line line;

    public Ray(double startX, double startY)
    {
        this.startPoints = new Point2D(startX, startY);
        this.direction = new Point2D(1, 0);
        this.line = new Line(0, 0, this.direction.getX() * 10, this.direction.getY() * 10);
        this.line.setTranslateX(this.startPoints.getX());
        this.line.setTranslateY(this.startPoints.getY());
        this.line.setStroke(Color.WHITE);
    }

    public boolean cast(Boundary boundary)
    {
        double x1 = boundary.getStartX();
        double y1 = boundary.getStartY();
        double x2 = boundary.getStopX();
        double y2 = boundary.getStopY();

        double x3 = this.startPoints.getX();
        double y3 = this.startPoints.getY();
        double x4 = this.startPoints.getX() + this.direction.getX();
        double y4 = this.startPoints.getY() + this.direction.getY();

        double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if(den == 0) // Exactement parallèle donc ne se touchent jamais
        {
            return false;
        }

        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
        double u = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / den;

        return t > 0 && t < 1 && u > 0;
    }

    public Point2D getStartPoints() {
        return startPoints;
    }
    public Point2D getDirection() {
        return direction;
    }
    public Line getLine() {
        return line;
    }
}
