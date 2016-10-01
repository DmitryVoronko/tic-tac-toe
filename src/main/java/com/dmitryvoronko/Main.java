package com.dmitryvoronko;

import com.dmitryvoronko.view.RootController;
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/root.fxml"));
        RootController controller = new RootController();
        loader.setController(controller);
        Parent root = loader.load();
        controller.setPrimaryStage(primaryStage);
        primaryStage.setTitle(R.GAME_NAME);
        primaryStage.setScene(new Scene(root, R.WINDOW_WIDTH, R.WINDOW_HEIGHT));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

}
