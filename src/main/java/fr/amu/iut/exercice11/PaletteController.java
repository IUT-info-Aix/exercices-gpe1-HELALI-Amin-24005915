package fr.amu.iut.exercice11;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class PaletteController {
    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    @FXML
    private Label texteDuHaut;

    @FXML
    private Button vert;

    @FXML
    private Button rouge;

    @FXML
    private Button bleu;

    @FXML
    private Pane panneau;

    @FXML
    private Label texteDuBas;

    @FXML
    public void initialize() {
        vert.setOnAction(event -> {
            nbVert++;
            panneau.setStyle("-fx-background-color: green");
            texteDuHaut.setText("Vert choisi " + nbVert + " fois");
            texteDuBas.setText("Vert");
        });

        rouge.setOnAction(event -> {
            nbRouge++;
            panneau.setStyle("-fx-background-color: red");
            texteDuHaut.setText("Rouge choisi " + nbRouge + " fois");
            texteDuBas.setText("Rouge");
        });

        bleu.setOnAction(event -> {
            nbBleu++;
            panneau.setStyle("-fx-background-color: blue");
            texteDuHaut.setText("Bleu choisi " + nbBleu + " fois");
            texteDuBas.setText("Bleu");
        });
    }
} 