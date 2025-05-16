package fr.amu.iut.exercice18;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

public class PanneauPrincipal {

    private static final double RATIO_DESSIN = 15.0; // Facteur multiplicatif pour le dessin
    private static final int VALEUR_MAX_COORDONNEES = 20;

    @FXML
    private Slider sliderXA;
    @FXML
    private TextField labelXA;
    @FXML
    private Slider sliderYA;
    @FXML
    private TextField labelYA;
    @FXML
    private TextField textFieldXB;
    @FXML
    private Button btnMoinsXB;
    @FXML
    private Button btnPlusXB;
    @FXML
    private TextField textFieldYB;
    @FXML
    private Button btnMoinsYB;
    @FXML
    private Button btnPlusYB;
    @FXML
    private TextField textFieldPerimeter;
    @FXML
    private Pane drawingPane;

    private Rectangle rectangle;

    private Line lineTop;
    private Line lineBottom;
    private Line lineLeftSide;
    private Line lineRightSide;

    @FXML
    public void initialize() {
        rectangle = new Rectangle();

        // Initialisation des lignes pour le dessin
        lineTop = new Line();
        lineBottom = new Line();
        lineLeftSide = new Line();
        lineRightSide = new Line();

        // Configuration des TextFormatter pour les champs de texte de B pour n'accepter que des entiers
        NumberFormat format = NumberFormat.getIntegerInstance();
        UnaryOperator<TextFormatter.Change> filter = c -> {
            if (c.isContentChange()) {
                ParsePosition parsePosition = new ParsePosition(0);
                // NumberFormat.parse() renvoie un Number, nous devons vérifier s'il s'agit d'un Integer
                Number parsedInput = format.parse(c.getControlNewText(), parsePosition);
                if (parsedInput == null || parsePosition.getIndex() < c.getControlNewText().length()) {
                    return null; // Rejette la modification
                }
                // Vérifier les bornes 0 et VALEUR_MAX_COORDONNEES
                int value = parsedInput.intValue();
                if (value < 0 || value > VALEUR_MAX_COORDONNEES) {
                    return null; // Rejette si hors bornes
                }
            }
            return c; // Accepte la modification
        };

        textFieldXB.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, filter));
        textFieldYB.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), 0, filter));

        bindSommetsRectangle();
        bindPerimeterTextField();
        addLines(); // Ajoute les lignes au Pane et configure leurs bindings
    }

    private void bindSommetsRectangle() {
        // Point A (Sliders)
        sliderXA.valueProperty().bindBidirectional(rectangle.xAProperty());
        labelXA.textProperty().bind(rectangle.xAProperty().asString());

        sliderYA.valueProperty().bindBidirectional(rectangle.yAProperty());
        labelYA.textProperty().bind(rectangle.yAProperty().asString());
        
        // Configurer les limites des sliders si ce n'est pas fait dans FXML (max est déjà à 20)
        sliderXA.setMin(0);
        sliderXA.setMax(VALEUR_MAX_COORDONNEES);
        sliderYA.setMin(0);
        sliderYA.setMax(VALEUR_MAX_COORDONNEES);

        // Point B (TextFields) - Utilisation de StringConverter pour la liaison bidirectionnelle
        StringConverter<Number> converter = new StringConverter<>() {
            @Override
            public String toString(Number object) {
                if (object == null) return "0";
                return object.toString();
            }

            @Override
            public Number fromString(String string) {
                try {
                    if (string == null || string.isEmpty()) return 0;
                    int val = Integer.parseInt(string);
                    if (val < 0) return 0;
                    if (val > VALEUR_MAX_COORDONNEES) return VALEUR_MAX_COORDONNEES;
                    return val;
                } catch (NumberFormatException e) {
                    return 0; // ou la valeur précédente
                }
            }
        };

        textFieldXB.textProperty().bindBidirectional(rectangle.xBProperty(), converter);
        textFieldYB.textProperty().bindBidirectional(rectangle.yBProperty(), converter);

        // Initialiser les TextFields avec les valeurs par défaut du rectangle
        textFieldXB.setText(String.valueOf(rectangle.getXB()));
        textFieldYB.setText(String.valueOf(rectangle.getYB()));
    }

    private void bindPerimeterTextField() {
        textFieldPerimeter.textProperty().bind(rectangle.perimetreProperty().asString());
    }

    private void addLines() {
        drawingPane.getChildren().addAll(lineTop, lineBottom, lineLeftSide, lineRightSide);

        // Les méthodes bindHorizontal1 et bindVertical1 sont spécifiques à un point A comme point de départ.
        bindHorizontal1(lineTop); // (xA,yA) -> (xB,yA)
        bindVertical1(lineLeftSide); // (xA,yA) -> (xA,yB)

        // Ligne horizontale opposée (partant de xA, yB vers xB, yB)
        lineBottom.startXProperty().bind(rectangle.xAProperty().multiply(RATIO_DESSIN));
        lineBottom.startYProperty().bind(rectangle.yBProperty().multiply(RATIO_DESSIN));
        lineBottom.endXProperty().bind(rectangle.xBProperty().multiply(RATIO_DESSIN));
        lineBottom.endYProperty().bind(rectangle.yBProperty().multiply(RATIO_DESSIN));

        // Ligne verticale opposée (partant de xB, yA vers xB, yB)
        lineRightSide.startXProperty().bind(rectangle.xBProperty().multiply(RATIO_DESSIN));
        lineRightSide.startYProperty().bind(rectangle.yAProperty().multiply(RATIO_DESSIN));
        lineRightSide.endXProperty().bind(rectangle.xBProperty().multiply(RATIO_DESSIN));
        lineRightSide.endYProperty().bind(rectangle.yBProperty().multiply(RATIO_DESSIN));
    }


    // 6. Implémentez les méthodes bindHorizontal1() et bindVertical1()
    private void bindHorizontal1(Line segment) {
        // Segment: (xA, yA) -> (xB, yA)
        segment.startXProperty().bind(rectangle.xAProperty().multiply(RATIO_DESSIN));
        segment.startYProperty().bind(rectangle.yAProperty().multiply(RATIO_DESSIN));
        segment.endXProperty().bind(rectangle.xBProperty().multiply(RATIO_DESSIN));
        segment.endYProperty().bind(rectangle.yAProperty().multiply(RATIO_DESSIN));
    }

    private void bindVertical1(Line segment) {
        // Segment: (xA, yA) -> (xA, yB)
        segment.startXProperty().bind(rectangle.xAProperty().multiply(RATIO_DESSIN));
        segment.startYProperty().bind(rectangle.yAProperty().multiply(RATIO_DESSIN));
        segment.endXProperty().bind(rectangle.xAProperty().multiply(RATIO_DESSIN));
        segment.endYProperty().bind(rectangle.yBProperty().multiply(RATIO_DESSIN));
    }


    // Méthodes pour les boutons +/-
    @FXML
    private void incrementerBx() {
        int currentValue = rectangle.getXB();
        if (currentValue < VALEUR_MAX_COORDONNEES) {
            rectangle.setXB(currentValue + 1);
        }
    }

    @FXML
    private void decrementerBx() {
        int currentValue = rectangle.getXB();
        if (currentValue > 0) {
            rectangle.setXB(currentValue - 1);
        }
    }

    @FXML
    private void setByPlusAction() { // Pour yB
        int currentValue = rectangle.getYB();
        if (currentValue < VALEUR_MAX_COORDONNEES) {
            rectangle.setYB(currentValue + 1);
        }
    }

    @FXML
    private void setByMinusAction() { // Pour yB
        int currentValue = rectangle.getYB();
        if (currentValue > 0) {
            rectangle.setYB(currentValue - 1);
        }
    }
} 