package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création du panneau Celsius
        VBox panneauCelsius = new VBox(10);
        Label labelCelsius = new Label("°C");
        labelCelsius.setStyle("-fx-font-weight: bold");
        
        Slider sliderCelsius = new Slider(0, 100, 0);
        sliderCelsius.setShowTickMarks(true);
        sliderCelsius.setShowTickLabels(true);
        sliderCelsius.setMajorTickUnit(10);
        sliderCelsius.setBlockIncrement(1);
        sliderCelsius.setPrefHeight(300);
        sliderCelsius.setOrientation(javafx.geometry.Orientation.VERTICAL);
        
        TextField textCelsius = new TextField("0.00");
        textCelsius.setPrefColumnCount(4);
        textCelsius.setEditable(true);
        
        panneauCelsius.getChildren().addAll(labelCelsius, sliderCelsius, textCelsius);
        panneauCelsius.setAlignment(javafx.geometry.Pos.CENTER);
        
        // Création du panneau Fahrenheit
        VBox panneauFahrenheit = new VBox(10);
        Label labelFahrenheit = new Label("°F");
        labelFahrenheit.setStyle("-fx-font-weight: bold");
        
        Slider sliderFahrenheit = new Slider(32, 212, 32);
        sliderFahrenheit.setShowTickMarks(true);
        sliderFahrenheit.setShowTickLabels(true);
        sliderFahrenheit.setMajorTickUnit(20);
        sliderFahrenheit.setBlockIncrement(1);
        sliderFahrenheit.setPrefHeight(300);
        sliderFahrenheit.setOrientation(javafx.geometry.Orientation.VERTICAL);
        
        TextField textFahrenheit = new TextField("32.00");
        textFahrenheit.setPrefColumnCount(4);
        textFahrenheit.setEditable(true);
        
        panneauFahrenheit.getChildren().addAll(labelFahrenheit, sliderFahrenheit, textFahrenheit);
        panneauFahrenheit.setAlignment(javafx.geometry.Pos.CENTER);

        // Binding bidirectionnel entre les sliders et les textFields
        Bindings.bindBidirectional(
            textCelsius.textProperty(),
            sliderCelsius.valueProperty(),
            new NumberStringConverter("#0.00")
        );
        
        Bindings.bindBidirectional(
            textFahrenheit.textProperty(),
            sliderFahrenheit.valueProperty(),
            new NumberStringConverter("#0.00")
        );
        
        // Binding bidirectionnel entre les valeurs Celsius et Fahrenheit
        // Formule: F = C * 9/5 + 32
        sliderCelsius.valueProperty().addListener((obs, oldVal, newVal) -> {
            double celsius = newVal.doubleValue();
            double fahrenheit = celsius * 9.0/5.0 + 32;
            sliderFahrenheit.setValue(fahrenheit);
        });
        
        sliderFahrenheit.valueProperty().addListener((obs, oldVal, newVal) -> {
            double fahrenheit = newVal.doubleValue();
            double celsius = (fahrenheit - 32) * 5.0/9.0;
            sliderCelsius.setValue(celsius);
        });

        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Convertisseur de températures");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}