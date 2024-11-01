package fr.univartois.butinfo.ihm.deuxmillequarantehuit.controller;

import fr.univartois.butinfo.ihm.deuxmillequarantehuit.model.Grid;
import fr.univartois.butinfo.ihm.deuxmillequarantehuit.model.MoveResult;
import fr.univartois.butinfo.ihm.deuxmillequarantehuit.model.Tile;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.Random;

public class DeuxMilleQuaranteHuitController {

    @FXML
    private GridPane mainGrid;

    @FXML
    private Label score;
    private int totalScore = 0;

    private Label[][] tileLabels = new Label[4][4];
    private Grid grid = new Grid();
    private Random random = new Random();

    @FXML
    void initialize() {
        for (int i = 0; i < tileLabels.length; i++) {
            for (int j = 0; j < tileLabels.length; j++) {
                Label label = new Label();
                mainGrid.add(label, j, i);
                tileLabels[i][j] = label;
            }
        }
        score.setText("0");
        generateTile();
        generateTile();
        updateGrid();
    }

    @FXML
    void restartGame() {
        grid = new Grid();
        totalScore = 0;
        generateTile();
        generateTile();
        updateGrid();
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                movementUp();
                break;
            case DOWN:
                movementDown();
                break;
            case LEFT:
                movementLeft();
                break;
            case RIGHT:
                movementRight();
                break;
            default:
                break;
        }
    }

    void movementUp() {
        try {
            MoveResult moveResult = grid.moveUp();
            if (moveResult.hasMoved()) {
                totalScore += moveResult.getMergeScore() + moveResult.getNumberOfMovedTiles();
                score.setText(String.valueOf(totalScore));
            }
            generateTile();
            updateGrid();
        } catch (Exception e) {
            System.err.println("Vous ne pouvez pas déplacer les tuiles vers le haut.");
        }
    }

    void movementDown() {
        try {
            MoveResult moveResult = grid.moveDown();
            if (moveResult.hasMoved()) {
                totalScore += moveResult.getMergeScore() + moveResult.getNumberOfMovedTiles();
                score.setText(String.valueOf(totalScore));
            }
            generateTile();
            updateGrid();
        } catch (Exception e) {
            System.err.println("Vous ne pouvez pas déplacer les tuiles vers le bas.");
        }
    }

    void movementLeft() {
        try {
            MoveResult moveResult = grid.moveLeft();
            if (moveResult.hasMoved()) {
                totalScore += moveResult.getMergeScore() + moveResult.getNumberOfMovedTiles();
                score.setText(String.valueOf(totalScore));
            }
            generateTile();
            updateGrid();
        } catch (Exception e) {
            System.err.println("Vous ne pouvez pas déplacer les tuiles vers la gauche.");
        }
    }

    void movementRight() {
        try {
            MoveResult moveResult = grid.moveRight();
            if (moveResult.hasMoved()) {
                totalScore += moveResult.getMergeScore() + moveResult.getNumberOfMovedTiles();
                score.setText(String.valueOf(totalScore));
            }
            generateTile();
            updateGrid();
        } catch (Exception e) {
            System.err.println("Vous ne pouvez pas déplacer les tuiles vers la droite.");
        }
    }

    void generateTile() {
        if (grid.isBlocked()) {
            score.setText("Game Over");
        } else {
            double p = random.nextDouble();
            if (p < 0.9) {
                grid.availableTiles().get(0).setValue(2);
            } else {
                grid.availableTiles().get(0).setValue(4);
            }
        }
    }

    void updateGrid() {
        for (int i = 0; i < tileLabels.length; i++) {
            for (int j = 0; j < tileLabels.length; j++) {
                Tile tile = grid.get(i, j);
                Label label = tileLabels[i][j];
                if (tile.isEmpty()) {
                    tileLabels[i][j].setText("");
                } else {
                    tileLabels[i][j].setText(String.valueOf(tile.getValue()));
                }
                label.setBackground(computeBackground(tile.getValue()));
                label.setAlignment(Pos.CENTER);
                label.setTextAlignment(TextAlignment.CENTER);
                label.setFont(new Font(25));
                label.setMaxWidth(78);
                label.setMaxHeight(78);
            }
        }
    }

    private static Background computeBackground(int value) {
        Color color;
        switch (value) {
            case 2: color = Color.web("#E3D3C2"); break;
            case 4: color = Color.web("#EEE0CB"); break;
            case 8: color = Color.web("#F1AD73"); break;
            case 16: color = Color.web("#F59462"); break;
            case 32: color = Color.web("#F67C5F"); break;
            case 64: color = Color.web("#F66645"); break;
            case 128: color = Color.web("#EDCE6F"); break;
            case 256: color = Color.web("#EDCD63"); break;
            case 512: color = Color.web("#212425"); break;
            case 1024: color = Color.web("#EEC84A"); break;
            case 2048: color = Color.web("#EEC73E"); break;
            default: color = Color.web("#FFFFFF"); break;
        }
        return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
    }
}