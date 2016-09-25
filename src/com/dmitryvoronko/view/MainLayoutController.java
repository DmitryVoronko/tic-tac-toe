package com.dmitryvoronko.view;

import com.dmitryvoronko.model.field.Field;
import com.dmitryvoronko.model.game.*;
import com.dmitryvoronko.model.player.Computer;
import com.dmitryvoronko.model.player.Player;
import com.dmitryvoronko.model.player.UserPlayer;
import com.dmitryvoronko.util.Ref;
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
import java.util.Optional;

/**
 * Created by Dmitry on 23/09/2016.
 */
public class MainLayoutController implements GameObserver {

    private final Ref<Move> lastMoveRef = new Ref<>();
    private Game game;
    private ArrayList<Button> field;
    @FXML
    private GridPane gridPane;
    private EventHandler<MouseEvent> clickCellHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            clickCell(event);
        }

        private void clickCell(MouseEvent event) {
            Button button = (Button) event.getSource();
            int row = ((GridPane) button.getParent()).getRowIndex(button);
            int column = ((GridPane) button.getParent()).getColumnIndex(button);
            lastMoveRef.setValue(new Move(row, column));
            game.makeTurn();
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

    private void createField() {
    }

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
            button.setOnMouseClicked(clickCellHandler);
        }

    }

    private void showGameOverDialog(State state) {
        String title;
        if (state.equals(State.WON)) {
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
        } else {
            newGame(Side.O);
        }
    }

    private void newGame(Side side) {
        if (game != null) {
            game.removeObserver(this);
        }
        clearField();
        chooseSide(side);
        game.registerObserver(this);
        game.makeTurn();
    }

    private void chooseSide(Side side) {
        switch (side) {
            case X:
                game = new Game((Field field, Side s) -> new UserPlayer(lastMoveRef, field, s), Computer::new);
                break;

            case O:
                game = new Game(Computer::new, (Field field, Side s) -> new UserPlayer(lastMoveRef, field, s));
                break;
        }
    }

    private void clearField() {
        for (Button button : field) {
            button.setDisable(false);
            button.setText("");
        }
    }

    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    public void playerMoved(Player player, Move move) {
        int row = move.getRow();
        int column = move.getColumn();
        Button button = (Button) getNodeByRowColumnIndex(row, column, gridPane);
        button.setText(player.getSide().name());
        button.setDisable(true);
    }

    public void gameStateChanged(State state) {
        showGameOverDialog(state);
    }
}
