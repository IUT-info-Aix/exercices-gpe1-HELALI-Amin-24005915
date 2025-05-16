package fr.amu.iut.exercice18;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RectanglePerimeterCalculatorAndDrawer extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fr/amu/iut/exercice18/fenetrePerimetre.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Calculateur et Dessinateur de Périmètre");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement du FXML pour l'exercice 18: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
} 