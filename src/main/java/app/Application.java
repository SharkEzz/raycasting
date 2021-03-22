package app;

import app.Raycasting.Boundary;
import app.Raycasting.Particle;
import app.Raycasting.Ray;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Application extends javafx.application.Application {
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Particle particle = new Particle();
        ArrayList<Boundary> walls = new ArrayList<>();
        walls.add(new Boundary(1, 1, 1, Application.HEIGHT - 50));
        walls.add(new Boundary(1, 1, Application.WIDTH, 1));
        walls.add(new Boundary(Application.WIDTH - 100, 1, Application.WIDTH - 100, Application.HEIGHT - 50));
        walls.add(new Boundary(1, Application.HEIGHT - 50, Application.WIDTH - 100, Application.HEIGHT - 50));
        //walls.add(new Boundary(400, 100, 400, 400));
        //walls.add(new Boundary(600, 400, 700, 650));

        for(int i = 0; i < 5; i++)
        {
            walls.add(new Boundary(Math.random() * 1200, Math.random() * 700, Math.random() * 1200, Math.random() * 700));
        }

        Pane rootPane = new Pane();
        rootPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        rootPane.getChildren().addAll(walls.stream().map(Boundary::getLine).collect(Collectors.toList()));

        for(Ray ray: particle.getRays())
        {
            rootPane.getChildren().add(ray.getLine());
        }

        Scene rootScene = new Scene(rootPane);
        rootScene.setFill(Color.BLACK);

        particle.look(walls);

        rootScene.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                particle.moveTo(event.getX(), event.getY(), walls);
            }
        });

        primaryStage.setScene(rootScene);
        primaryStage.setTitle("Didou raycasting");
        primaryStage.setWidth(Application.WIDTH);
        primaryStage.setHeight(Application.HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
