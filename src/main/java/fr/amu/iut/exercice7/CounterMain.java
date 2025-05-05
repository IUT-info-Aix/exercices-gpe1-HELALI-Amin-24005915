package fr.amu.iut.exercice7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class CounterMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            BorderPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CounterView.fxml")));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}