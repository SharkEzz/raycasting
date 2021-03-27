package app.Raycasting;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Ray {
    private final Line shape;
    private final Point2D direction;

    public Ray(Point2D position, double angle)
    {
        this.direction = new Point2D(Math.cos(angle), Math.sin(angle));
        this.shape = new Line(position.getX(), position.getY(), position.getX() + this.direction.getX() * 20, position.getY() + this.direction.getY() * 20) {
            {
                setStroke(Color.WHITE);
                setOpacity(.5f);
            }
        };
    }

    /**
     * Retourne le point d'intersection, -1, -1 si aucune intersection
     * @param boundary
     * @return
     */
    public Point2D cast(Boundary boundary)
    {
        double x1 = boundary.getStartX();
        double y1 = boundary.getStartY();
        double x2 = boundary.getStopX();
        double y2 = boundary.getStopY();

        double x3 = this.shape.getStartX();
        double y3 = this.shape.getStartY();
        double x4 = this.shape.getEndX();
        double y4 = this.shape.getEndY();

        double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if(den == 0)
            return new Point2D(-1, -1);

        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
        double u = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / den;

        if(t >= 0 && t <= 1 && u >= 0)
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
        this.shape.setStartX(x);
        this.shape.setStartY(y);
        this.shape.setEndX(x + this.direction.getX() * 20);
        this.shape.setEndY(y + this.direction.getY() * 20);
    }

    public void setStop(Point2D stop)
    {
        this.shape.setEndX(stop.getX());
        this.shape.setEndY(stop.getY());
    }

    public Line getShape() {
        return shape;
    }
}
