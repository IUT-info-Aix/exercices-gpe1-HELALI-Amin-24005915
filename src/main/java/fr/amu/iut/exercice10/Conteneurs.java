package fr.amu.iut.exercice10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Conteneurs extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            URL fxmlURL = getClass().getResource("/fr/amu/iut/exercice10/ConteneursView.fxml");
            if (fxmlURL == null) {
                System.err.println("Le fichier FXML n'a pas pu être trouvé");
                return;
            }
            
            BorderPane root = FXMLLoader.load(fxmlURL);
            stage.setScene(new Scene(root));
            stage.setTitle("Premier exemple manipulant les conteneurs");
            stage.show();
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier FXML:");
            e.printStackTrace();
        }
    }
}