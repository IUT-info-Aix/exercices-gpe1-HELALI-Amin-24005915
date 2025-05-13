package fr.amu.iut.exercice15;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    private Button okButton;

    @FXML
    private Button cancelButton;

    private BooleanProperty okButtonDisabled;

    private void createBindings() {
        okButtonDisabled = new SimpleBooleanProperty(true);
        okButton.disableProperty().bind(okButtonDisabled);

        BooleanBinding userIdEmpty = userId.textProperty().isEmpty();
        BooleanBinding pwdEmpty = pwd.textProperty().isEmpty();

        okButtonDisabled.bind(userIdEmpty.or(pwdEmpty));
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