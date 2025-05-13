package fr.amu.iut.exercice15;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {
    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;
    
    @FXML
    private Button okBtn;
    
    @FXML
    public void initialize() {
        createBindings();
    }

    private void createBindings() {
        // Désactiver le bouton OK si un des champs est vide
        okBtn.disableProperty().bind(
            userId.textProperty().isEmpty().or(pwd.textProperty().isEmpty())
        );
    }

    @FXML
    private void okClicked() {
        System.out.print(userId.getText() + " ");
        for (char c : pwd.getText().toCharArray()) {
            System.out.print("*");
        }
        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        userId.setText("");
        pwd.setText("");
    }
}