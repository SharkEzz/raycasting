package app;

import app.Raycasting.Boundary;
import app.Raycasting.Ray;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Boundary boundary = new Boundary(300, 100, 300, 300);
        Ray ray = new Ray(200, 250);

        Pane rootPane = new Pane();
        rootPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        rootPane.getChildren().addAll(boundary.getLine(), ray.getLine());

        if(ray.cast(boundary))
        {
            System.out.println("OUI !");
        }

        Scene rootScene = new Scene(rootPane);
        rootScene.setFill(Color.BLACK);

        primaryStage.setScene(rootScene);
        primaryStage.setTitle("Didou raycasting");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
