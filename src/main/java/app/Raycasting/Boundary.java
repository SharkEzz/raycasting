package app.Raycasting;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class Boundary
{
    private final Line line;

    public Boundary(double startX, double startY, double stopX, double stopY)
    {
        this.line = new Line(startX, startY, stopX, stopY);
        this.line.setStroke(Color.WHITE);
    }

    public Line getLine()
    {
        return this.line;
    }
    public double getStartX()
    {
        return this.line.getStartX();
    }
    public double getStartY()
    {
        return this.line.getStartY();
    }
    public double getStopX()
    {
        return this.line.getEndX();
    }
    public double getStopY()
    {
        return this.line.getEndY();
    }
}
