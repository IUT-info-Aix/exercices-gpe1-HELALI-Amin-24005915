package fr.amu.iut.exercice8;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControl {

    // Liens vers les composants définis dans le fichier FXML
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    // Action associée au bouton Login
    @FXML
    private void handleLoginAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Error: Both fields are required.");
        } else {
            System.out.println("Login successful for user: " + username);
        }
    }

    // Action associée au bouton Cancel
    @FXML
    private void handleCancelAction() {
        usernameField.clear();
        passwordField.clear();
        System.out.println("Fields cleared.");
    }
}