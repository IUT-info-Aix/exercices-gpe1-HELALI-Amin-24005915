package fr.amu.iut.exercice18;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Rectangle {
    private IntegerProperty xA, yA, xB, yB;
    private IntegerProperty perimetre;
    // Propriétés pour largeur et hauteur, utiles pour les bindings
    private IntegerProperty largeur, hauteur;

    public Rectangle() {
        this.xA = new SimpleIntegerProperty(0);
        this.yA = new SimpleIntegerProperty(0);
        this.xB = new SimpleIntegerProperty(10); // Valeurs initiales pour un rectangle visible
        this.yB = new SimpleIntegerProperty(5);
        this.perimetre = new SimpleIntegerProperty();
        this.largeur = new SimpleIntegerProperty();
        this.hauteur = new SimpleIntegerProperty();

        // Liaison pour la largeur
        largeur.bind(Bindings.createIntegerBinding(() -> {
            return Math.abs(xB.get() - xA.get());
        }, xA, xB));
        
        // Liaison pour la hauteur
        hauteur.bind(Bindings.createIntegerBinding(() -> {
            return Math.abs(yB.get() - yA.get());
        }, yA, yB));
        
        // Liaison pour le périmètre
        perimetre.bind(largeur.add(hauteur).multiply(2));
    }

    public int getXA() {
        return xA.get();
    }

    public IntegerProperty xAProperty() {
        return xA;
    }

    public void setXA(int xA) {
        this.xA.set(xA);
    }

    public int getYA() {
        return yA.get();
    }

    public IntegerProperty yAProperty() {
        return yA;
    }

    public void setYA(int yA) {
        this.yA.set(yA);
    }

    public int getXB() {
        return xB.get();
    }

    public IntegerProperty xBProperty() {
        return xB;
    }

    public void setXB(int xB) {
        this.xB.set(xB);
    }

    public int getYB() {
        return yB.get();
    }

    public IntegerProperty yBProperty() {
        return yB;
    }

    public void setYB(int yB) {
        this.yB.set(yB);
    }

    public int getPerimetre() {
        return perimetre.get();
    }

    public IntegerProperty perimetreProperty() {
        return perimetre;
    }

    public IntegerProperty largeurProperty() {
        return largeur;
    }

    public int getLargeur() { return largeur.get(); }

    public IntegerProperty hauteurProperty() {
        return hauteur;
    }
    public int getHauteur() { return hauteur.get(); }
} 