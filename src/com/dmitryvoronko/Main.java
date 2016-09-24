package com.dmitryvoronko;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/MainLayout.fxml"));
        primaryStage.setTitle(R.GAME_NAME);
        primaryStage.setScene(new Scene(root, R.WINDOW_WIDTH, R.WINDOW_HEIGHT));
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
