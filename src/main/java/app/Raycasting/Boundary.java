package app.Raycasting;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Boundary {
    private final Line shape;

    public Boundary(double startX, double startY, double stopX, double stopY)
    {
        this.shape = new Line(startX, startY, stopX, stopY) {
            {
                setStroke(Color.WHITE);
            }
        };
    }

    public Line getShape() {
        return shape;
    }

    public double getStartX()
    {
        return this.shape.getStartX();
    }

    public double getStartY()
    {
        return this.shape.getStartY();
    }

    public double getStopX()
    {
        return this.shape.getEndX();
    }

    public double getStopY()
    {
        return this.shape.getEndY();
    }
}
