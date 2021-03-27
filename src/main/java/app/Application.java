package app;

import app.Raycasting.Boundary;
import app.Raycasting.Particle;
import app.Raycasting.Ray;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Application extends javafx.application.Application {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Particle particle = new Particle();
        ArrayList<Ray> rays = particle.getRays();

        ArrayList<Boundary> walls = new ArrayList<>();

        walls.add(new Boundary(0, 0, 700, 0));
        walls.add(new Boundary(700, 0, 700, 550));
        walls.add(new Boundary(700, 550, 0, 550));
        walls.add(new Boundary(0, 550, 0, 0));

        for(int i = 0; i < 5; i++)
        {
            walls.add(new Boundary(Math.random() * 700, Math.random() * 550, Math.random() * 700, Math.random() * 550));
        }

        for(Boundary wall: walls)
        {
            pane.getChildren().add(wall.getShape());
        }

        for(Ray ray: rays)
        {
            pane.getChildren().add(ray.getShape());
        }

        particle.moveTo(WIDTH / 2f, HEIGHT / 2f, walls);

        Scene scene = new Scene(pane);

        scene.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                particle.moveTo(event.getX(), event.getY(), walls);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Raycasting");
        primaryStage.setResizable(false);
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.show();
    }
}
