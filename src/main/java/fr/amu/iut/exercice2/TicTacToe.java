package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;



public class TicTacToe extends Application {
    @Override
    public void start(Stage primaryStage) {
        //importation des image
        Image imgCroix = new Image(getClass().getResourceAsStream("/exercice2/Croix.png"));
        ImageView Croix   = new ImageView(imgCroix);
        Label  labelCroix = new Label();
        labelCroix.setGraphic(Croix);

        Image imgRond = new Image(getClass().getResourceAsStream("/exercice2/Rond.png"));
        ImageView Rond   = new ImageView(imgRond);
        Label  labelRond = new Label();
        labelRond.setGraphic(Rond);

        Image imgVide = new Image(getClass().getResourceAsStream("/exercice2/Vide.png"));
        ImageView Vide   = new ImageView(imgVide);
        Label  labelVide = new Label();
        labelVide.setGraphic(Vide);

        //Créationd de la grid
        GridPane gridpane = new GridPane();
        gridpane.setHgap(2);
        gridpane.setVgap(3);






        primaryStage.setTitle("TicTacToe");
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

