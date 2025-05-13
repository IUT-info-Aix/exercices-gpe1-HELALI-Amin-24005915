package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {
    private DoubleProperty celsius = new SimpleDoubleProperty(0);
    private DoubleProperty fahrenheit = new SimpleDoubleProperty(32);

    @Override
    public void start(Stage primaryStage) {
        // Création du panneau Celsius
        VBox panneauCelsius = new VBox(10);
        Label labelCelsius = new Label("Celsius:");
        TextField textCelsius = new TextField();
        Slider sliderCelsius = new Slider(-100, 100, 0);
        sliderCelsius.setShowTickMarks(true);
        sliderCelsius.setShowTickLabels(true);
        sliderCelsius.setMajorTickUnit(50);
        sliderCelsius.setMinorTickCount(5);
        panneauCelsius.getChildren().addAll(labelCelsius, textCelsius, sliderCelsius);
        
        // Création du panneau Fahrenheit
        VBox panneauFahrenheit = new VBox(10);
        Label labelFahrenheit = new Label("Fahrenheit:");
        TextField textFahrenheit = new TextField();
        Slider sliderFahrenheit = new Slider(-148, 212, 32);
        sliderFahrenheit.setShowTickMarks(true);
        sliderFahrenheit.setShowTickLabels(true);
        sliderFahrenheit.setMajorTickUnit(50);
        sliderFahrenheit.setMinorTickCount(5);
        panneauFahrenheit.getChildren().addAll(labelFahrenheit, textFahrenheit, sliderFahrenheit);

        // Binding bidirectionnel entre les propriétés et les contrôles
        // Celsius <-> TextField et Slider
        StringConverter<Number> converter = new NumberStringConverter();
        Bindings.bindBidirectional(textCelsius.textProperty(), celsius, converter);
        sliderCelsius.valueProperty().bindBidirectional(celsius);
        
        // Fahrenheit <-> TextField et Slider
        Bindings.bindBidirectional(textFahrenheit.textProperty(), fahrenheit, converter);
        sliderFahrenheit.valueProperty().bindBidirectional(fahrenheit);
        
        // Binding de conversion entre Celsius et Fahrenheit
        // F = C * 9/5 + 32
        // C = (F - 32) * 5/9
        NumberBinding celsiusToFahrenheit = celsius.multiply(9.0/5.0).add(32);
        NumberBinding fahrenheitToCelsius = fahrenheit.subtract(32).multiply(5.0/9.0);
        
        // Utilisation de l'astuce du ChangeListener à ne déclencher qu'une seule fois
        fahrenheit.addListener((obs, oldVal, newVal) -> {
            if (Math.abs(newVal.doubleValue() - celsiusToFahrenheit.getValue().doubleValue()) > 0.01) {
                celsius.set(fahrenheitToCelsius.getValue().doubleValue());
            }
        });
        
        celsius.addListener((obs, oldVal, newVal) -> {
            if (Math.abs(newVal.doubleValue() - fahrenheitToCelsius.getValue().doubleValue()) > 0.01) {
                fahrenheit.set(celsiusToFahrenheit.getValue().doubleValue());
            }
        });
        
        // Initialisation
        celsius.set(0);

        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Convertisseur de températures");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}