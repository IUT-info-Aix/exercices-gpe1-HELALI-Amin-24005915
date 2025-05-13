package fr.amu.iut.exercice14;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personne {

    private StringProperty nom;
    private IntegerProperty age;
    private StringProperty villeDeNaissance;

    public Personne(String nom, int age) {
        this.nom = new SimpleStringProperty(nom);
        this.age = new SimpleIntegerProperty(age);
        this.villeDeNaissance = new SimpleStringProperty("Paris");
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getVilleDeNaissance() {
        return villeDeNaissance.get();
    }

    public StringProperty villeDeNaissanceProperty() {
        return villeDeNaissance;
    }

    public void setVilleDeNaissance(String ville) {
        this.villeDeNaissance.set(ville);
    }
}
