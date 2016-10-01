package com.dmitryvoronko.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * Created by Dmitry on 28/09/2016.
 */
class FieldView extends GridPane {

    public FieldView(int length, EventHandler<ActionEvent> eventHandler) {
        fillGrid(length);
        setAlignment(Pos.CENTER);
        setNodesAlignments();
        setEventHandler(eventHandler);
    }

    public void displayMove(int row, int column, String value) {
        Button button = (Button) getNodeByRowColumnIndex(row, column);
        button.setText(value);
        button.setDisable(true);
    }

    public void clear() {
        ObservableList<Node> children = getChildren();
        for (Node node : children) {
            Button button = (Button) node;
            button.setDisable(false);
            button.setText("");
        }
    }

    private void setEventHandler(EventHandler<ActionEvent> eventHandler) {
        for (Node node : getChildren()) {
            Button currentButton = (Button) node;
            currentButton.setOnAction(eventHandler);
        }
    }

    private void fillGrid(int length) {
        for (int row = 0; row < length; row++) {
            for (int column = 0; column < length; column++) {
                Button button = new Button();
                add(button, row, column);
                button.setStyle("\t-fx-background-color: -swatch-grey;\n" +
                        "\t-fx-pref-height: 60;\n" +
                        "\t-fx-pref-width: 60;\n" +
                        "\t-fx-min-height: 60;\n" +
                        "\t-fx-min-width: 60;\n" +
                        "\t-fx-padding: 0;" +
                        "\t-fx-font-family: 'MaterialDesignIconicFont';\n" +
                        "\t-fx-font-size: 34px;" +
                        "\t-fx-margin: 20;");
            }
        }
    }

    private void setNodesAlignments() {
        ObservableList<Node> children = getChildren();
        for (Node node : children) {
            setHalignment(node, HPos.CENTER);
            setValignment(node, VPos.CENTER);
            Insets insets = new Insets(2);
            setMargin(node, insets);
        }
    }

    private Node getNodeByRowColumnIndex(final int row, final int column) {
        Node result = null;
        ObservableList<Node> children = getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

}
