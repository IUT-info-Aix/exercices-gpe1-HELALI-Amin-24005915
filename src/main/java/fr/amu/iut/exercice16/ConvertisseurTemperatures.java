package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConvertisseurTemperatures extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox panneauCelsius = new VBox(30);
        VBox panneauFahrenheit = new VBox(30);

        Label celsiusLabel = new Label("Celsius");
        Label fahrenheitLabel = new Label("Fahrenheit");

        TextField celsiusField = new TextField();
        TextField fahrenheitField = new TextField();

        DoubleProperty celsius = new SimpleDoubleProperty();
        DoubleProperty fahrenheit = new SimpleDoubleProperty();

        // Binding bidirectionnel entre Celsius et Fahrenheit
        Bindings.bindBidirectional(celsiusField.textProperty(), celsius, new javafx.util.StringConverter<Number>() {
            @Override
            public String toString(Number value) {
                return String.format("%.1f", value);
            }

            @Override
            public Number fromString(String string) {
                try {
                    return Double.parseDouble(string);
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        });

        Bindings.bindBidirectional(fahrenheitField.textProperty(), fahrenheit, new javafx.util.StringConverter<Number>() {
            @Override
            public String toString(Number value) {
                return String.format("%.1f", value);
            }

            @Override
            public Number fromString(String string) {
                try {
                    return Double.parseDouble(string);
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        });

        // Conversion Celsius vers Fahrenheit
        fahrenheit.bind(celsius.multiply(9.0/5.0).add(32));

        // Conversion Fahrenheit vers Celsius
        celsius.bind(fahrenheit.subtract(32).multiply(5.0/9.0));

        panneauCelsius.getChildren().addAll(celsiusLabel, celsiusField);
        panneauFahrenheit.getChildren().addAll(fahrenheitLabel, fahrenheitField);

        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Convertisseur de Températures");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}