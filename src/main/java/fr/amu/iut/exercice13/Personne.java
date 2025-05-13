package fr.amu.iut.exercice13;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personne {

    private String nom;
    private int age;
    private StringProperty villeDeNaissance;

    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = age;
        this.villeDeNaissance = new SimpleStringProperty("Paris");
    }

    public String getNom() {
        return nom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
    
    public StringProperty villeDeNaissanceProperty() {
        return villeDeNaissance;
    }
    
    public String getVilleDeNaissance() {
        return villeDeNaissance.get();
    }
    
    public void setVilleDeNaissance(String ville) {
        villeDeNaissance.set(ville);
    }
    
    @Override
    public String toString() {
        return nom + " (" + age + " ans)";
    }
}
