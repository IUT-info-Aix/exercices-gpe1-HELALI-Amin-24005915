package fr.amu.iut.exercice8;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {

    @FXML
    private TextField name;
    @FXML
    private PasswordField pwd;

    @FXML
    private void okClicked() {
        StringBuilder pw = new StringBuilder();
        for (int i = 0; i < pwd.getText().length(); i++) {
            pw.append('*');
        }
        System.out.println("Nom d'utilisateur: " + name.getText());
        System.out.println("Mot de passe: " + pw);
    }

    @FXML
    private void cancelClicked() {
        name.setText("");
        pwd.setText("");
        System.out.println("Formulaire réinitialisé");
    }
}