package fr.amu.iut.exercice10;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConteneursController {

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button bouton1;

    @FXML
    private Button bouton2;

    @FXML
    private Button bouton3;

    @FXML
    protected void onSubmitButtonClick() {
        System.out.println("Submit button clicked");
    }

    @FXML
    protected void onCancelButtonClick() {
        System.out.println("Cancel button clicked");
    }

    @FXML
    protected void onBouton1Click() {
        System.out.println("Bouton 1 clicked");
    }

    @FXML
    protected void onBouton2Click() {
        System.out.println("Bouton 2 clicked");
    }

    @FXML
    protected void onBouton3Click() {
        System.out.println("Bouton 3 clicked");
    }
} 