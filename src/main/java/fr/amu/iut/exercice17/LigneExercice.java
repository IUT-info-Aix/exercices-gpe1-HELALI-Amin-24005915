package fr.amu.iut.exercice17;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class LigneExercice {
    private Exercice exercice;
    private Label enonceLabel;
    private TextField reponseField;
    private HBox layout;

    public LigneExercice(Exercice exercice) {
        this.exercice = exercice;
        
        // Créer le label avec l'énoncé et le styliser avec un fond vert
        this.enonceLabel = new Label(exercice.getEnonce());
        this.enonceLabel.setPrefWidth(100);
        this.enonceLabel.setStyle("-fx-background-color: lightgreen; -fx-padding: 5px; -fx-alignment: CENTER_RIGHT;");
        
        // Champ de réponse
        this.reponseField = new TextField();
        this.reponseField.setPrefWidth(50); // Largeur fixe pour le champ de réponse

        // Layout pour contenir les deux éléments
        this.layout = new HBox(10);
        this.layout.setAlignment(Pos.CENTER_LEFT);
        this.layout.getChildren().addAll(enonceLabel, reponseField);
    }

    public Node getNode() {
        return layout;
    }

    public String getReponseUtilisateur() {
        return reponseField.getText();
    }

    public Exercice getExercice() {
        return exercice;
    }

    public void setCorrect(boolean correct) {
        if (correct) {
            reponseField.setStyle("-fx-background-color: lightgreen;");
        } else {
            reponseField.setStyle("-fx-background-color: pink;");
        }
    }
} 