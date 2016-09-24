package com.dmitryvoronko.view;

import com.dmitryvoronko.model.Game;
import com.dmitryvoronko.model.Side;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

/**
 * Created by Dmitry on 23/09/2016.
 */
public class MainLayoutController implements Observer {

    private Game game;
    private String userChoice;
    private ArrayList<Button> field;
    private EventHandler<MouseEvent> choiceCellHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            Button button = (Button) event.getSource();
            button.setText("X");
            button.setDisable(true);
            int row = ((GridPane) button.getParent()).getRowIndex(button);
            int column = ((GridPane) button.getParent()).getColumnIndex(button);
            game.move(row, column, Side.X);
        }
    };
    @FXML
    private VBox choicePlayerBox;
    private EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            userChoice = ((Button) event.getSource()).getText();
            choicePlayerBox.getParent().getChildrenUnmodifiable().remove(choicePlayerBox);
        }
    };

    @FXML
    private Button buttonCell00;
    @FXML
    private Button buttonCell01;
    @FXML
    private Button buttonCell02;
    @FXML
    private Button buttonCell10;
    @FXML
    private Button buttonCell11;
    @FXML
    private Button buttonCell12;
    @FXML
    private Button buttonCell20;
    @FXML
    private Button buttonCell21;
    @FXML
    private Button buttonCell22;

    @FXML
    public void startPlayerX() {

    }

    @FXML
    public void startPlayerY() {

    }

    @FXML
    private void initialize() {
        newGame(Side.X);
        field = new ArrayList<>();
        field.add(buttonCell00);
        field.add(buttonCell01);
        field.add(buttonCell02);
        field.add(buttonCell10);
        field.add(buttonCell11);
        field.add(buttonCell12);
        field.add(buttonCell20);
        field.add(buttonCell21);
        field.add(buttonCell22);

        for (Button button : field) {
            button.setOnMouseClicked(choiceCellHandler);
        }
    }

    public void update(Observable o, Object arg) {
        if (o instanceof Game) {
            System.out.println("CHTOTO PROIZOSHLO" + game.getState());
        }
    }

    private void showGameOverDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Начать новую игру?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            newGame(Side.X);
        } else {
            System.exit(0);
        }
    }

    private void newGame(Side side) {
        game = new Game(side);
        game.addObserver(this);
    }
}
