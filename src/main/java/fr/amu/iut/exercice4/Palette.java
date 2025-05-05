package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Palette extends Application  {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialiser le BorderPane racine
        root = new BorderPane();

        // Initialiser les boutons
        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        // Initialiser le label et le placer en haut (centré)
        label = new Label();
        BorderPane.setAlignment(label, javafx.geometry.Pos.CENTER);
        root.setTop(label);

        // Initialiser le panneau (paneau central)
        panneau = new Pane();
        panneau.setPrefSize(400, 200);
        root.setCenter(panneau) ;

        // Initialiser le HBox pour les boutons et le placer en bas
        HBox buttons = new HBox(10, vert, rouge, bleu);
        buttons.setAlignment(javafx.geometry.Pos.CENTER);
        buttons.setStyle("-fx-padding: 10px;");
        root.setBottom(buttons);

        // Configurer les actions des boutons
        vert.setOnAction(e -> {
            nbVert++;// Incrémentation du compteur nbVert
            panneau.setStyle("-fx-background-color: green;");
            label.setText("Vert sélectionné " + nbVert + " fois");// Affichage du compteur
        });

        rouge.setOnAction(e -> {
            nbRouge++;// Incrémentation du compteur nbRouge
            panneau.setStyle("-fx-background-color: red;");
            label.setText("Rouge sélectionné " + nbRouge + " fois");// Affichage du compteur
        });

        bleu.setOnAction(e -> {
            nbBleu++;// Incrémentation du compteur nbBleu
            panneau.setStyle("-fx-background-color: blue;");
            label.setText("Bleu sélectionné " + nbBleu + " fois");// Affichage du compteur
        });

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
