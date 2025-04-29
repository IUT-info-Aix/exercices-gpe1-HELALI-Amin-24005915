package fr.amu.iut.exercice5;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;

    // Suivi de l'état du jeu
    private boolean gameRunning = false;
    private Timeline gameTimer;
    private int remainingSeconds = 10;
    private Label timerLabel;
    private Label statusLabel;

    // Liste statique de tableaux pour stocker les obstacles
    public static ArrayList<Obstacle> obstacles = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();

        // Placez Pacman sur le côté gauche du jeu.
        pacman.setLayoutX(20);
        pacman.setLayoutY(240);

        // Placez le fantôme sur le côté droit du jeu.
        fantome.setLayoutX(600);
        fantome.setLayoutY(240);

        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);

        // Créer des obstacles
        // Obstacle central
        Obstacle centerObstacle = new Obstacle(300, 200, 40, 80);
        obstacles.add(centerObstacle);

        // Mur du haut
        Obstacle topWall = new Obstacle(150, 100, 340, 20);
        obstacles.add(topWall);

        // Paroi inférieure
        Obstacle bottomWall = new Obstacle(150, 360, 340, 20);
        obstacles.add(bottomWall);

        // Ajouter des obstacles au volet
        for (Obstacle obstacle : obstacles) {
            jeu.getChildren().add(obstacle);
        }

        // Créer et configurer une étiquette de temporisation
        timerLabel = new Label("Temps: 10s");
        timerLabel.setFont(new Font("Arial", 20));
        timerLabel.setTextFill(Color.BLACK);
        timerLabel.setLayoutX(20);
        timerLabel.setLayoutY(20);

        // Créer et configurer l'étiquette de statut
        statusLabel = new Label("Attrapez le fantôme en 10 secondes !");
        statusLabel.setFont(new Font("Arial", 20));
        statusLabel.setTextFill(Color.BLACK);
        statusLabel.setLayoutX(200);
        statusLabel.setLayoutY(20);

        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        jeu.getChildren().add(timerLabel);
        jeu.getChildren().add(statusLabel);
        root.setCenter(jeu);
        //on construit une scene 640 * 480 pixels
        scene = new Scene(root);

        //Gestion du déplacement du personnage
        deplacer(pacman, fantome);

        // Démarrer le chronomètre du jeu
        startGameTimer(pacman, fantome);

        primaryStage.setTitle("... Pac Man - Mode rapide ...");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Démarre le chronomètre de jeu de 10 secondes
     * @param pacman Le personnage de Pacman
     * @param ghost Le personnage du Fantôme
     */
    private void startGameTimer(Personnage pacman, Personnage ghost) {
        // Initialisation de la minuterie
        gameRunning = true;
        remainingSeconds = 10;

        // Créer le calendrier du compte à rebours
        gameTimer = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> {
                remainingSeconds--;
                timerLabel.setText("Time: " + remainingSeconds + "s");

                // Vérifier si le temps est écoulé
                if (remainingSeconds <= 0) {
                    gameTimer.stop();
                    gameRunning = false;

                    // Le fantôme gagne si le temps est écoulé
                    ghostWins();
                }
            })
        );

        // Régler la minuterie pour qu'elle se répète 10 fois (une fois par seconde)
        gameTimer.setCycleCount(10);
        gameTimer.play();
    }

    /**
     * Appelé quand Pacman attrape le fantôme
     */
    private void pacmanWins() {
        gameTimer.stop();
        gameRunning = false;

        statusLabel.setText("Pacman gagne !");
        statusLabel.setTextFill(Color.GREEN);

        // Afficher le dialogue d'alerte
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fin du jeu");
        alert.setHeaderText("Pacman gagne !");
        alert.setContentText("Pacman a attrapé le fantôme dans " + (10 - remainingSeconds) + " seconds!");

        // Afficher l'alerte et quitter l'application
        alert.showAndWait().ifPresent(response -> Platform.exit());
    }

    /**
     * Appelé lorsque le fantôme s'échappe pendant 10 secondes
     */
    private void ghostWins() {
        statusLabel.setText("Le fantôme gagne !");
        statusLabel.setTextFill(Color.RED);

        // Afficher le dialogue d'alerte
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fin du jeu");
        alert.setHeaderText("Le fantôme gagne !");
        alert.setContentText("Le fantôme a échappé à Pacman pendant 10 secondes !");

        // Afficher l'alerte et quitter l'application
        alert.showAndWait().ifPresent(response -> Platform.exit());
    }

    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1(up,down, right, left), pour le j2( z,q,s,d)
     *
     * @param j1 Le premier personnage (Pacman)
     * @param j2 Le deuxième caractère (Fantome)
     */
    private void deplacer(Personnage j1, Personnage j2) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            // Ne traiter les événements clés que si le jeu est en cours d'exécution
            if (gameRunning) {
                switch (event.getCode()) {
                    case LEFT:
                        j1.deplacerAGauche();
                        break;
                    case RIGHT:
                        j1.deplacerADroite(scene.getWidth());
                        break;
                    case UP:
                        j1.deplacerEnHaut(scene.getHeight());
                        break;
                    case DOWN:
                        j1.deplacerEnBas(scene.getHeight());
                        break;
                    case Z:
                        //j2...... vers le haut;
                        j2.deplacerEnHaut(scene.getHeight());
                        break;
                    case S:
                        j2.deplacerEnBas(scene.getHeight());
                        break;
                    case Q:
                        j2.deplacerAGauche();
                        break;
                    case D:
                        j2.deplacerADroite(scene.getWidth());
                        break;
                }

                // Vérification des collisions entre les caractères
                if (j1.estEnCollision(j2)) {
                    System.out.println("Collision....");

                    // Pacman gagne quand il attrape le fantôme
                    pacmanWins();
                }
            }
        });
    }


}
