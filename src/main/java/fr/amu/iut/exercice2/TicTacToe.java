package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Random;

public class TicTacToe extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        // Chargement des images
        Image imgCroix = new Image(getClass().getResourceAsStream("/exercice2/Croix.png"));
        ImageView Croix = new ImageView(imgCroix);
        Croix.setFitWidth(90);
        Croix.setFitHeight(90);


        Image imgRond = new Image(getClass().getResourceAsStream("/exercice2/Rond.png"));
        ImageView Rond = new ImageView(imgRond);
        Rond.setFitWidth(90);
        Rond.setFitHeight(90);

        Image imgVide = new Image(getClass().getResourceAsStream("/exercice2/Vide.png"));
        ImageView Vide = new ImageView(imgVide);
        Vide.setFitWidth(90);
        Vide.setFitHeight(90);




        // Création de la grille
        GridPane gridpane = new GridPane();

        // Placement aléatoire des éléments
        Random random = new Random();
        Label label;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int choix = random.nextInt(3);
                if (choix == 0) {
                    label = new Label();
                    label.setGraphic(Croix);
                } else if (choix == 1) {
                    label = new Label();
                    label.setGraphic(Rond);
                } else {
                    label = new Label();
                    label.setGraphic(Vide);
                }
                GridPane.setRowIndex(label, row);
                GridPane.setColumnIndex(label, col);
                gridpane.getChildren().add(label);
            }
        }

        // Configuration de la fenêtre
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(true);
        primaryStage.setTitle("TicTacToe");

        primaryStage.setScene(new Scene(gridpane, 300, 300));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}