package fr.amu.iut.exercice17;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AppMaths extends Application {

    private ComboBox<Integer> nombreExercicesComboBox;
    private VBox exercicesPane;
    private Button voirResultatButton;
    private Label resultatLabel;

    private List<Exercice> tousLesExercices = new ArrayList<>();
    private List<LigneExercice> lignesExercicesUI = new ArrayList<>();

    private static final int NOMBRE_OPERATIONS_A_GENERER = 50; // Un grand nombre pour avoir de la variete

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Exercices d'arithmétique");

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.TOP_LEFT); // Alignement à gauche comme dans l'image

        // Étape 1: Choix du nombre d'exercices
        HBox choixNombreBox = new HBox(10);
        choixNombreBox.setAlignment(Pos.CENTER_LEFT);
        Label combienLabel = new Label("Combien d'exercices ?");
        nombreExercicesComboBox = new ComboBox<>();
        nombreExercicesComboBox.getItems().addAll(6, 9, 12, 15);
        nombreExercicesComboBox.setValue(6); // Valeur par défaut
        nombreExercicesComboBox.setPrefWidth(100); // Largeur fixe comme dans l'image
        choixNombreBox.getChildren().addAll(combienLabel, nombreExercicesComboBox);

        // Zone pour afficher les exercices
        exercicesPane = new VBox(5);
        exercicesPane.setSpacing(8); // Espacement entre les lignes d'exercices
        exercicesPane.setPadding(new Insets(10, 0, 10, 0));
        ScrollPane scrollPane = new ScrollPane(exercicesPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(300); // Hauteur ajustable
        scrollPane.setStyle("-fx-background-color: transparent;"); // Fond transparent

        // Bouton Voir le résultat
        voirResultatButton = new Button("Voir le résultat");
        HBox buttonBox = new HBox(voirResultatButton);
        buttonBox.setAlignment(Pos.CENTER);

        resultatLabel = new Label(); // Pour afficher le score ou les erreurs

        // Initialisation des exercices et gestion des événements
        genererTousLesExercices();
        nombreExercicesComboBox.setOnAction(event -> afficherExercices());
        voirResultatButton.setOnAction(event -> validerReponses());

        // Affichage initial
        afficherExercices();

        root.getChildren().addAll(choixNombreBox, scrollPane, buttonBox, resultatLabel);

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void genererTousLesExercices() {
        tousLesExercices.clear();
        Random random = new Random();
        String[] operateurs = {"+", "-", "*", "/"};

        for (int i = 0; i < NOMBRE_OPERATIONS_A_GENERER; ++i) {
            int a = random.nextInt(20) + 1; // Nombres entre 1 et 20
            int b = random.nextInt(10) + 1; // Nombres entre 1 et 10 pour la division et soustraction
            String op = operateurs[random.nextInt(operateurs.length)];
            int resultatAttendu;
            String enonce;

            switch (op) {
                case "+":
                    resultatAttendu = a + b;
                    enonce = a + " + " + b + " =";
                    break;
                case "-":
                    // Assurer a >= b pour éviter résultats négatifs simples
                    if (a < b) { int temp = a; a = b; b = temp; }
                    resultatAttendu = a - b;
                    enonce = a + " - " + b + " =";
                    break;
                case "*":
                    resultatAttendu = a * b;
                    enonce = a + " * " + b + " =";
                    break;
                case "/":
                    // Assurer une division entière simple
                    resultatAttendu = a; // Le dividende devient le résultat attendu
                    int produit = a * b; // On calcule le nombre à diviser
                    enonce = produit + " / " + b + " =";
                    // On échange a avec produit pour que 'a' dans l'objet Exercice soit le premier operande de l'enonce
                    a = produit;
                    break;
                default: // Ne devrait pas arriver
                    throw new IllegalStateException("Opérateur inconnu: " + op);
            }
            tousLesExercices.add(new Exercice(enonce, resultatAttendu));
        }
    }


    private void afficherExercices() {
        exercicesPane.getChildren().clear();
        lignesExercicesUI.clear();
        resultatLabel.setText(""); // Réinitialiser le message de résultat

        int nombreASafficher = nombreExercicesComboBox.getValue();
        Collections.shuffle(tousLesExercices); // Mélanger pour de la variété à chaque fois

        for (int i = 0; i < nombreASafficher && i < tousLesExercices.size(); ++i) {
            Exercice exo = tousLesExercices.get(i);
            LigneExercice ligneUI = new LigneExercice(exo);
            lignesExercicesUI.add(ligneUI);
            exercicesPane.getChildren().add(ligneUI.getNode());
        }
    }

    private void validerReponses() {
        int nombreChoisi = nombreExercicesComboBox.getValue();
        List<Exercice> exercicesIncorrects = new ArrayList<>();
        int score = 0;

        for (LigneExercice ligneUI : lignesExercicesUI) {
            try {
                int reponseUtilisateur = Integer.parseInt(ligneUI.getReponseUtilisateur());
                if (ligneUI.getExercice().verifierReponse(reponseUtilisateur)) {
                    score++;
                    ligneUI.setCorrect(true);
                } else {
                    exercicesIncorrects.add(ligneUI.getExercice());
                    ligneUI.setCorrect(false);
                }
            } catch (NumberFormatException e) {
                // Réponse non numérique est considérée comme incorrecte
                exercicesIncorrects.add(ligneUI.getExercice());
                ligneUI.setCorrect(false);
            }
        }

        resultatLabel.setText("Votre score : " + score + " / " + lignesExercicesUI.size());

        if (!exercicesIncorrects.isEmpty()) {
            System.out.println("Exercices incorrects initialement: " + exercicesIncorrects.size());
            // Point 7: si le nombre d'exercices incorrectement répondus est supérieur au choix n de la ComboBox,
            // on affichera les n premiers exercices incorrects.
            if (exercicesIncorrects.size() > nombreChoisi) {
                System.out.println("Trop d'erreurs, réaffichage des " + nombreChoisi + " premiers incorrects.");
                exercicesPane.getChildren().clear();
                lignesExercicesUI.clear();
                int count = 0;
                for (Exercice exoIncorrect : exercicesIncorrects) {
                    if (count < nombreChoisi) {
                        LigneExercice nouvelleLigneUI = new LigneExercice(exoIncorrect);
                        nouvelleLigneUI.setCorrect(false); // Marquer comme incorrect pour le style
                        lignesExercicesUI.add(nouvelleLigneUI);
                        exercicesPane.getChildren().add(nouvelleLigneUI.getNode());
                        count++;
                    } else {
                        break;
                    }
                }
            } else {
                 // Optionnel: on pourrait juste marquer les erreurs sur l'UI existante
                 // ou réafficher uniquement les incorrects s'il y en a peu.
                 // L'énoncé demande de regénérer toute la liste si on change le nombre d'exercices.
                 // Ici, on se contente de marquer les corrects/incorrects
                 System.out.println(exercicesIncorrects.size() + " erreurs, mise à jour du style.");
                 // La mise à jour du style est gérée par LigneExercice.setCorrect()
            }
        }
    }
} 