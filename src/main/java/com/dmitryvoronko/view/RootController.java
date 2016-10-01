package com.dmitryvoronko.view;

import com.dmitryvoronko.Main;
import com.dmitryvoronko.R;
import com.dmitryvoronko.model.game.Game;
import com.dmitryvoronko.model.game.GameObserver;
import com.dmitryvoronko.model.game.Move;
import com.dmitryvoronko.model.game.Side;
import com.dmitryvoronko.model.player.Player;
import com.dmitryvoronko.util.Ref;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by Dmitry on 23/09/2016.
 */
public class RootController implements GameObserver {
    private final Ref<Move> lastMoveRef = new Ref<>();
    @FXML
    private VBox root;
    private Stage primaryStage;
    private FieldView fieldView;
    private TitledPane menuView;
    private Game game;

    @FXML
    private void initialize() {
        loadMenuView();
        loadFieldView();
        newGame(Game.Type.WITH_COMPUTER, Side.O);
    }

    public void newGame(Game.Type gameType, Side side) {
        menuView.setExpanded(false);
        if (game != null) {
            game.removeObserver(this);
        }
        fieldView.clear();
        game = new Game(gameType, side, lastMoveRef);
        game.registerObserver(this);
        game.makeTurn();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void playerMoved(Player player, Move move) {
        int row = move.getRow();
        int column = move.getColumn();
        String value = player.getSide().name();
        fieldView.displayMove(row, column, value);
    }

    public void gameOverWithDraw() {
        showTooltip("Ничья!");
    }

    public void gameOverWithWin(Side winnerSide) {
        showTooltip("Победа " + winnerSide.name() + "!");
    }

    private void showTooltip(String text) {
        Tooltip tooltip = new Tooltip(text);
        tooltip.setAutoHide(true);
        tooltip.show(primaryStage);
    }

    private EventHandler<ActionEvent> getEventHandler() {
        return event -> {
            Button button = (Button) event.getSource();
            int row = GridPane.getRowIndex(button);
            int column = GridPane.getColumnIndex(button);
            lastMoveRef.setValue(new Move(row, column));
            game.makeTurn();
        };
    }

    private void loadFieldView() {
        EventHandler<ActionEvent> eventHandler = getEventHandler();
        fieldView = new FieldView(R.FIELD_LENGTH, eventHandler);
        root.getChildren().add(fieldView);
    }

    private void loadMenuView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/menu.fxml"));
            MenuController controller = new MenuController();
            loader.setController(controller);
            menuView = loader.load();
            controller.setRoot(this);
            root.getChildren().add(menuView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
