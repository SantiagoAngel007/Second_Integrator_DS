package com.example.busquedadeltesoro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }

    public HelloApplication() {
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load(), 600.0, 400.0);
        stage.setTitle("Tresure!");
        stage.setScene(scene);
        stage.show();
    }

    public static void showWindow(String fxml, double width, double height) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root, width, height);
            Stage stage = new Stage();
//            Canvas canvas = new Canvas(width, height);
//            root.getChildrenUnmodifiable().add(canvas);
            stage.setTitle("Treasure Hunt!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException var9) {
            var9.printStackTrace();
        }
    }

    public static void hideWindow(Stage stage) {
        stage.hide();
    }

    public static void main(String[] args) {
        launch();
    }
}