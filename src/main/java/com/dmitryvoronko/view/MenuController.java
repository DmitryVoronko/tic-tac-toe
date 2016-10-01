package com.dmitryvoronko.view;

import com.dmitryvoronko.model.game.Game;
import com.dmitryvoronko.model.game.Side;
import com.dmitryvoronko.view.RootController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

/**
 * Created by Dmitry on 28/09/2016.
 */
public class MenuController {
    private Side side;
    private Game.Type gameType;
    private RootController root;
    @FXML
    private RadioButton withComputer;
    @FXML
    private RadioButton withFriend;
    @FXML
    private RadioButton sideX;
    @FXML
    private RadioButton sideO;
    @FXML
    private Button newGameButton;

    public void setRoot(RootController root) {
        this.root = root;
    }


    @FXML
    private void initialize() {
        chooseWithComputer();
        chooseChoiceSideO();
    }

    @FXML
    private void chooseWithComputer() {
        gameType = Game.Type.WITH_COMPUTER;
        setCurrentGameType(gameType);
    }

    @FXML
    private void chooseWithFriend() {
        gameType = Game.Type.WITH_FRIEND;
        setCurrentGameType(gameType);
    }

    @FXML
    private void chooseChoiceSideX() {
        side = Side.X;
        setCurrentSide(side);
    }

    @FXML
    private void chooseChoiceSideO() {
        side = Side.O;
        setCurrentSide(side);
    }

    @FXML
    private void exit() {
        System.exit(0);
    }

    @FXML
    public void newGame() {
        root.newGame(gameType, side);
    }

    private void setCurrentGameType(Game.Type gameType) {
        switch (gameType) {
            case WITH_COMPUTER:
                setSelected(withComputer, withFriend);
                break;
            case WITH_FRIEND:
                setSelected(withFriend, withComputer);
        }
    }

    private void setCurrentSide(Side side) {
        switch (side) {
            case X:
                setSelected(sideX, sideO);
            case O:
                setSelected(sideO, sideX);
        }
    }

    private void setSelected(RadioButton first, RadioButton second) {
        first.setSelected(true);
        second.setSelected(false);
    }

}
