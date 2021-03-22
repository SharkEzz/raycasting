package app.Raycasting;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Ray
{
    private Point2D position;
    private Point2D direction;
    private final Line line;

    public Ray(Point2D position, double angle)
    {
        this.position = position;
        this.direction = new Point2D(Math.cos(angle), Math.sin(angle));
        this.line = new Line(position.getX(), position.getY(), position.getX() + this.direction.getX() * 20, position.getY() + this.direction.getY() * 20);
        this.line.setStroke(Color.WHITE);
        this.line.setOpacity(.4);
    }

    public void lookAt(double x, double y)
    {
        this.line.setEndX(x);
        this.line.setEndY(y);
    }

    public Point2D cast(Boundary boundary)
    {
        double x1 = boundary.getStartX();
        double y1 = boundary.getStartY();
        double x2 = boundary.getStopX();
        double y2 = boundary.getStopY();

        double x3 = this.position.getX();
        double y3 = this.position.getY();
        double x4 = this.position.getX() + this.direction.getX();
        double y4 = this.position.getY() + this.direction.getY();

        double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if(den == 0) // Exactement parallèle donc ne se touchent jamais
        {
            return new Point2D(-1, -1);
        }

        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
        double u = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / den;

        if(t > 0 && t < 1 && u > 0)
        {
            double pX = x1 + t * (x2 - x1);
            double pY = y1 + t * (y2 - y1);
            return new Point2D(pX, pY);
        }
        else
            return new Point2D(-1, -1);
    }

    public void setOrigin(double x, double y)
    {
        this.position = new Point2D(x, y);
        this.line.setStartX(x);
        this.line.setStartY(y);
        this.line.setEndX(position.getX() + this.direction.getX() * 20);
        this.line.setEndY(position.getY() + this.direction.getY() * 20);
    }

    public Point2D getPosition() {
        return position;
    }
    public Point2D getDirection() {
        return direction;
    }
    public Line getLine() {
        return line;
    }
}
