package app.Raycasting;

import app.Application;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class Particle {
    private ArrayList<Ray> rays;
    private Point2D position;

    public Particle(double x, double y)
    {
        this.position = new Point2D(x, y);
        this.initRays();
    }

    public Particle()
    {
        this.position = new Point2D(Application.WIDTH / 2f, Application.HEIGHT / 2f);
        this.initRays();
    }

    public ArrayList<Ray> getRays()
    {
        return this.rays;
    }

    public void moveTo(double x, double y, ArrayList<Boundary> boundaries)
    {
        this.position = new Point2D(x, y);

        for(Ray ray: this.rays)
        {
            ray.setOrigin(x, y);
            double infinity = Double.POSITIVE_INFINITY;
            Point2D closest = new Point2D(-1, -1);
            for(Boundary boundary: boundaries)
            {
                Point2D intersect = ray.cast(boundary);
                if(intersect.getX() != -1 && intersect.getY() != -1)
                {
                    // Calcul distance entre 2 vecteurs
                    double d = Math.sqrt(Math.pow(intersect.getX() - this.position.getX(), 2) + Math.pow(intersect.getY() - this.position.getY(), 2));

                    if(d < infinity)
                    {
                        infinity = d;
                        closest = new Point2D(intersect.getX(), intersect.getY());
                    }
                }
            }

            if(closest.getX() != -1 && closest.getY() != -1)
                ray.setStop(closest);
            else
                ray.setStop(new Point2D(ray.getShape().getEndX(), ray.getShape().getEndY()));
        }
    }

    public Point2D getPosition() {
        return position;
    }

    private void initRays()
    {
        this.rays = new ArrayList<>();

        for(int i = 0; i < 360; i += 3)
        {
            this.rays.add(new Ray(new Point2D(this.position.getX(), this.position.getY()), Math.toRadians(i)));
        }
    }
}
