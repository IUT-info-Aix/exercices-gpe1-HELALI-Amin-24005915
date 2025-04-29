package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class IHMPendu extends Application {

    private Canvas canvas;
    private GraphicsContext gc;
    private Label livesLabel;
    private Label wordLabel;
    private Button replayButton;
    private GridPane keyboardGrid;
    private ArrayList<Button> letterButtons;

    private Dico dico;
    private String currentWord;
    private String displayedWord;
    private int lives;
    private boolean gameOver;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(400);
        primaryStage.setHeight(750);

        // Initialize game variables
        dico = new Dico();
        letterButtons = new ArrayList<>();

        // Create main layout
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(22);
        root.setPadding(new Insets(10));

        // Create canvas for drawing the hangman
        canvas = new Canvas(600, 350);
        gc = canvas.getGraphicsContext2D();

        // Create labels
        livesLabel = new Label();
        livesLabel.setFont(Font.font("System", FontWeight.BOLD, 22));
        livesLabel.setTextFill(Color.rgb(51, 51, 51));

        wordLabel = new Label();
        wordLabel.setFont(Font.font("System", FontWeight.BOLD, 34));
        wordLabel.setTextFill(Color.rgb(51, 51, 51));

        // Create keyboard
        keyboardGrid = createKeyboard();

        // Create replay button
        replayButton = createReplayButton();

        // Add all components to the root layout
        root.getChildren().addAll(canvas, livesLabel, wordLabel, keyboardGrid, replayButton);

        // Create scene with specified background color
        Scene scene = new Scene(root, 600, 650, Color.rgb(228, 251, 245));

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);

        // Start a new game
        startNewGame();

        primaryStage.show();
    }

    private GridPane createKeyboard() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(4);
        grid.setVgap(4);

        // Define keyboard layout
        String[] row1 = {"a", "e", "i", "o", "u", "y"};
        String[] row2 = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m"};
        String[] row3 = {"n", "p", "q", "r", "s", "t", "v", "w", "x", "z"};

        // Create buttons for first row (with offset)
        for (int i = 0; i < row1.length; i++) {
            Button btn = createLetterButton(row1[i]);
            grid.add(btn, i + 2, 0); // Add offset to center the first row
            letterButtons.add(btn);
        }

        // Create buttons for second row
        for (int i = 0; i < row2.length; i++) {
            Button btn = createLetterButton(row2[i]);
            grid.add(btn, i, 1);
            letterButtons.add(btn);
        }

        // Create buttons for third row
        for (int i = 0; i < row3.length; i++) {
            Button btn = createLetterButton(row3[i]);
            grid.add(btn, i, 2);
            letterButtons.add(btn);
        }

        return grid;
    }

    private Button createLetterButton(String letter) {
        Button btn = new Button(letter);
        btn.setPrefSize(38, 38);
        btn.setStyle("-fx-background-color: #E4FBF5; " +
                     "-fx-border-color: #FFC78C; " +
                     "-fx-border-width: 2px; " +
                     "-fx-border-radius: 10px; " +
                     "-fx-background-radius: 10px; " +
                     "-fx-text-fill: #41CEC1; " +
                     "-fx-font-size: 16px;");

        btn.setOnAction(e -> handleLetterClick(letter.charAt(0), btn));

        return btn;
    }

    private Button createReplayButton() {
        Button btn = new Button("Rejouer");
        btn.setPrefSize(100, 35);
        btn.setStyle("-fx-background-color: transparent; " +
                     "-fx-border-color: #2ECCBF; " +
                     "-fx-border-width: 3px; " +
                     "-fx-border-radius: 18px; " +
                     "-fx-background-radius: 18px; " +
                     "-fx-text-fill: #FFA177; " +
                     "-fx-font-size: 18px; " +
                     "-fx-font-weight: bold;");

        btn.setOnAction(e -> startNewGame());

        // Hover effect
        btn.setOnMouseEntered(e -> 
            btn.setStyle("-fx-background-color: #FFC78C; " +
                         "-fx-border-color: #FFC78C; " +
                         "-fx-border-width: 3px; " +
                         "-fx-border-radius: 18px; " +
                         "-fx-background-radius: 18px; " +
                         "-fx-text-fill: #FFA177; " +
                         "-fx-font-size: 18px; " +
                         "-fx-font-weight: bold;")
        );

        btn.setOnMouseExited(e -> 
            btn.setStyle("-fx-background-color: transparent; " +
                         "-fx-border-color: #2ECCBF; " +
                         "-fx-border-width: 3px; " +
                         "-fx-border-radius: 18px; " +
                         "-fx-background-radius: 18px; " +
                         "-fx-text-fill: #FFA177; " +
                         "-fx-font-size: 18px; " +
                         "-fx-font-weight: bold;")
        );

        return btn;
    }

    private void startNewGame() {
        // Reset game state
        lives = 7;
        gameOver = false;

        // Get a new random word
        currentWord = dico.getMot();

        // Create masked word (all asterisks)
        char[] masked = new char[currentWord.length()];
        Arrays.fill(masked, '*');
        displayedWord = new String(masked);

        // Update UI
        livesLabel.setText("Nombre de vies : " + lives);
        wordLabel.setText(displayedWord);

        // Reset all letter buttons
        for (Button btn : letterButtons) {
            btn.setDisable(false);
            btn.setStyle("-fx-background-color: #E4FBF5; " +
                         "-fx-border-color: #FFC78C; " +
                         "-fx-border-width: 2px; " +
                         "-fx-border-radius: 10px; " +
                         "-fx-background-radius: 10px; " +
                         "-fx-text-fill: #41CEC1; " +
                         "-fx-font-size: 16px;");
        }

        // Draw initial gibbet
        drawGibbet();
    }

    private void handleLetterClick(char letter, Button btn) {
        if (gameOver) return;

        // Disable the button
        btn.setDisable(true);
        btn.setStyle("-fx-background-color: #E4FBF5; " +
                     "-fx-border-color: #FFC78C; " +
                     "-fx-border-width: 2px; " +
                     "-fx-border-radius: 10px; " +
                     "-fx-background-radius: 10px; " +
                     "-fx-text-fill: #C8C8C8; " +
                     "-fx-font-size: 16px;");

        // Check if the letter is in the word
        ArrayList<Integer> positions = dico.getPositions(letter, currentWord);

        if (positions.isEmpty()) {
            // Wrong guess
            lives--;
            livesLabel.setText("Nombre de vies : " + lives);
            updateHangmanDrawing();

            // Check if game is over
            if (lives <= 0) {
                gameOver = true;
                wordLabel.setText(currentWord);
                // Disable all letter buttons when game is over
                disableAllLetterButtons();
            }
        } else {
            // Correct guess - update displayed word
            char[] wordChars = displayedWord.toCharArray();
            for (int pos : positions) {
                wordChars[pos] = letter;
            }
            displayedWord = new String(wordChars);
            wordLabel.setText(displayedWord);

            // Check if player has won
            if (!displayedWord.contains("*")) {
                gameOver = true;
                // Display win message
                livesLabel.setText("Félicitations ! Vous avez gagné !");
                // Draw happy face or other win indication
                drawWinIndication();
                // Disable all letter buttons when game is over
                disableAllLetterButtons();
            }
        }
    }

    private void drawGibbet() {
        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Set line properties
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(4);
        gc.setLineCap(StrokeLineCap.ROUND);

        // Draw base horizontal line
        gc.strokeLine(100, 220, 303, 220);

        // Draw vertical pole
        gc.strokeLine(150, 100, 150, 220);

        // Draw top horizontal beam
        gc.strokeLine(150, 100, 230, 100);

        // Draw small vertical rope line
        gc.strokeLine(230, 100, 230, 125);

        // Draw diagonal support
        gc.strokeLine(150, 100, 170, 120);
    }

    private void updateHangmanDrawing() {
        switch (lives) {
            case 6: // Draw head
                gc.setFill(Color.rgb(255, 178, 203));
                gc.fillOval(230 - 19, 145 - 19, 38, 38);
                gc.strokeOval(230 - 19, 145 - 19, 38, 38);
                // Draw rope connecting to head
                gc.strokeLine(230, 125, 230, 126);
                break;
            case 5: // Draw body
                gc.strokeLine(230, 165, 230, 220);
                break;
            case 4: // Draw left arm
                gc.strokeLine(230, 180, 200, 200);
                break;
            case 3: // Draw right arm
                gc.strokeLine(230, 180, 260, 200);
                break;
            case 2: // Draw left leg
                gc.strokeLine(230, 220, 210, 250);
                break;
            case 1: // Draw right leg
                gc.strokeLine(230, 220, 250, 250);
                break;
            case 0: // Game over - add final details
                // Draw sad face
                gc.setFill(Color.RED);
                // Eyes (X shape)
                gc.strokeLine(220, 135, 230, 145);
                gc.strokeLine(230, 135, 220, 145);
                gc.strokeLine(240, 135, 230, 145);
                gc.strokeLine(230, 135, 240, 145);
                // Sad mouth
                gc.strokeLine(220, 155, 240, 155);
                gc.strokeLine(220, 155, 215, 150);
                gc.strokeLine(240, 155, 245, 150);

                // Display game over message
                livesLabel.setText("Game Over! Vous avez perdu!");
                break;
        }
    }

    /**
     * Draws a happy face and other visual elements to indicate the player has won
     */
    private void drawWinIndication() {
        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw happy face
        gc.setFill(Color.rgb(255, 215, 0)); // Gold color
        gc.fillOval(canvas.getWidth()/2 - 50, canvas.getHeight()/2 - 50, 100, 100);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(canvas.getWidth()/2 - 50, canvas.getHeight()/2 - 50, 100, 100);

        // Draw eyes
        gc.setFill(Color.BLACK);
        gc.fillOval(canvas.getWidth()/2 - 30, canvas.getHeight()/2 - 30, 15, 15);
        gc.fillOval(canvas.getWidth()/2 + 15, canvas.getHeight()/2 - 30, 15, 15);

        // Draw smile
        gc.setLineWidth(3);
        gc.strokeArc(canvas.getWidth()/2 - 30, canvas.getHeight()/2 - 10, 60, 40, 0, 180, javafx.scene.shape.ArcType.OPEN);

        // Draw trophy or stars
        drawStar(canvas.getWidth()/2 - 80, canvas.getHeight()/2 - 80, 20);
        drawStar(canvas.getWidth()/2 + 60, canvas.getHeight()/2 - 80, 20);
        drawStar(canvas.getWidth()/2 - 80, canvas.getHeight()/2 + 60, 20);
        drawStar(canvas.getWidth()/2 + 60, canvas.getHeight()/2 + 60, 20);
    }

    /**
     * Disables all letter buttons when the game is over
     */
    private void disableAllLetterButtons() {
        for (Button btn : letterButtons) {
            btn.setDisable(true);
            btn.setStyle("-fx-background-color: #E4FBF5; " +
                         "-fx-border-color: #FFC78C; " +
                         "-fx-border-width: 2px; " +
                         "-fx-border-radius: 10px; " +
                         "-fx-background-radius: 10px; " +
                         "-fx-text-fill: #C8C8C8; " +
                         "-fx-font-size: 16px;");
        }
    }

    /**
     * Draws a star at the specified position
     */
    private void drawStar(double x, double y, double size) {
        double innerRadius = size * 0.4;
        double outerRadius = size;

        double[] xPoints = new double[10];
        double[] yPoints = new double[10];

        for (int i = 0; i < 10; i++) {
            double radius = (i % 2 == 0) ? outerRadius : innerRadius;
            double angle = Math.PI / 5 * i;
            xPoints[i] = x + radius * Math.sin(angle);
            yPoints[i] = y - radius * Math.cos(angle);
        }

        gc.setFill(Color.YELLOW);
        gc.beginPath();
        gc.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < 10; i++) {
            gc.lineTo(xPoints[i], yPoints[i]);
        }
        gc.closePath();
        gc.fill();
        gc.stroke();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
