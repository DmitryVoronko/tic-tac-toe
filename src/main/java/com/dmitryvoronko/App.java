package com.dmitryvoronko;

import com.dmitryvoronko.controller.RootController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/MainLayout.fxml"));
        Parent root = loader.load();
        RootController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.setTitle(R.GAME_NAME);
        primaryStage.setScene(new Scene(root, R.WINDOW_WIDTH, R.WINDOW_HEIGHT));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

}
