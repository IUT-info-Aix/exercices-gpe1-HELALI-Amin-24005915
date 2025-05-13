package fr.amu.iut.exercice9;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Cette classe crée une animation où un bouton personnalisé se déplace
 * selon un mouvement carré dans le sens horaire, puis revient à sa position initiale.
 * Au clic sur le bouton, l'animation démarre.
 */
public class Animation extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        CustomButton customButton = new CustomButton();
        root.setCenter(customButton);
        Scene scene = new Scene(root, 400, 400);

        Duration duration = Duration.millis(1500);
        
        // Transition 1 : déplacement en haut à droite
        TranslateTransition transition1 = new TranslateTransition(duration, customButton);
        transition1.setByX(150);
        transition1.setByY(-150);
        transition1.setAutoReverse(false);
        
        // Transition 2 : déplacement en bas à droite
        TranslateTransition transition2 = new TranslateTransition(duration, customButton);
        transition2.setByX(0);
        transition2.setByY(300);
        transition2.setAutoReverse(false);
        
        // Transition 3 : déplacement en bas à gauche
        TranslateTransition transition3 = new TranslateTransition(duration, customButton);
        transition3.setByX(-300);
        transition3.setByY(0);
        transition3.setAutoReverse(false);
        
        // Transition 4 : déplacement en haut à gauche
        TranslateTransition transition4 = new TranslateTransition(duration, customButton);
        transition4.setByX(0);
        transition4.setByY(-300);
        transition4.setAutoReverse(false);
        
        // Transition 5 : retour à la position initiale
        TranslateTransition transition5 = new TranslateTransition(duration, customButton);
        transition5.setByX(150);
        transition5.setByY(150);
        transition5.setAutoReverse(false);

        // Crée une séquence de transitions et la configure pour s'exécuter deux fois
        SequentialTransition st = new SequentialTransition(transition1, transition2, transition3, transition4, transition5);
        st.setAutoReverse(true);
        st.setCycleCount(2);

        // Lance l'animation au clic sur le bouton
        customButton.addOnMousePressed(mouseEvent -> st.play());

        primaryStage.setTitle("Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}