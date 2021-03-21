package app;

import app.Raycasting.Boundary;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

        Pane rootPane = new Pane();
        rootPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        rootPane.getChildren().addAll(new Label("oui"), boundary.getLine());

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
