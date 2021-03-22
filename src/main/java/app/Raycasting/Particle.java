package app.Raycasting;

import app.Application;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Particle
{
    private Point2D position;
    private final ArrayList<Ray> rays;

    public Particle()
    {
        this.position = new Point2D(Application.WIDTH / 2f, Application.HEIGHT / 2f);
        this.rays = new ArrayList<>();
        for(int i = 0; i < 360; i += 3)
        {
            this.rays.add(new Ray(this.position, Math.toRadians(i)));
        }
    }

    public void look(ArrayList<Boundary> walls)
    {
        for(Ray ray: this.rays)
        {
            double record = Double.POSITIVE_INFINITY;
            Point2D closest = new Point2D(-1, -1);
            for(Boundary boundary: walls)
            {
                Point2D intersect = ray.cast(boundary);
                if(intersect.getY() != -1 && intersect.getX() != -1)
                {
                    double d = Math.sqrt(Math.pow(intersect.getX() - this.position.getX(), 2) + Math.pow(intersect.getY() - this.position.getY(), 2));

                    if(d < record)
                    {
                        record = d;
                        closest = new Point2D(intersect.getX(), intersect.getY());
                    }
                }
            }
            if(closest.getX() == -1 || closest.getY() == -1)
            {
                ray.lookAt(this.position.getX(), this.position.getY());
            }
            else
            {
                ray.lookAt(closest.getX(), closest.getY());
            }
        }
    }

    public void moveTo(double x, double y, ArrayList<Boundary> list)
    {
        for(Ray ray: this.rays)
        {
            ray.setOrigin(x, y);
        }

        this.position = new Point2D(x, y);
        this.look(list);
    }

    public ArrayList<Ray> getRays()
    {
        return this.rays;
    }
}
