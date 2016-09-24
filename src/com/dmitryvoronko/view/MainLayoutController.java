package com.dmitryvoronko.view;

import com.dmitryvoronko.model.Cell;
import com.dmitryvoronko.model.Game;
import com.dmitryvoronko.model.Side;
import com.dmitryvoronko.model.State;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

/**
 * Created by Dmitry on 23/09/2016.
 */
public class MainLayoutController implements Observer {

    private Side userSide;
    private Side computerSide;
    private Game game;
    private ArrayList<Button> field;
    private EventHandler<MouseEvent> choiceCellHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            Button button = (Button) event.getSource();
            button.setText(userSide.name());
            button.setDisable(true);
            int row = ((GridPane) button.getParent()).getRowIndex(button);
            int column = ((GridPane) button.getParent()).getColumnIndex(button);
            game.move(row, column, userSide);
        }
    };

    @FXML
    private GridPane gridPane;
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
        showDialog();
        for (Button button : field) {
            button.setOnMouseClicked(choiceCellHandler);
        }

    }

    public void update(Observable o, Object arg) {
        if (o instanceof Game) {
            if (game.getState() != State.RUN) {
                showGameOverDialog();
            } else {
                Cell computerMove = (Cell) arg;
                int row = computerMove.getRow();
                int column = computerMove.getColumn();
                Button button = (Button) getNodeByRowColumnIndex(row, column, gridPane);
                button.setText(computerSide.name());
                button.setDisable(true);
            }
        }
    }

    private void showGameOverDialog() {
        String title;
        if (game.getState().equals(State.WON)) {
            title = "Победа " + game.getWinner().name();
        } else {
            title = "Ничья";
        }
        showDialog(title);
    }

    private void showDialog(String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText("Начать новую игру?");

        ButtonType buttonTypeOne = new ButtonType("Да");
        ButtonType buttonTypeTwo = new ButtonType("Выход");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            showDialog();
        } else {
            System.exit(0);
        }
    }

    private void showDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Выберите сторону");
        alert.setHeaderText("Выберите X или O");
        alert.setContentText("Выберите сторону");

        ButtonType buttonTypeOne = new ButtonType("X");
        ButtonType buttonTypeTwo = new ButtonType("O");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            newGame(Side.X);
            userSide = Side.X;
            computerSide = Side.O;
        } else {
            newGame(Side.O);
            userSide = Side.O;
            computerSide = Side.X;
        }
    }

    private void newGame(Side side) {
        clearField();
        game = new Game(side);
        game.addObserver(this);
    }

    private void clearField() {
        for (Button button : field) {
            button.setDisable(false);
            button.setText("");
        }
    }

    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }
}
