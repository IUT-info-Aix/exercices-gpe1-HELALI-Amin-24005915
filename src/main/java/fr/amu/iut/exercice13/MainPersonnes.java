package fr.amu.iut.exercice13;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@SuppressWarnings("Duplicates")
public class MainPersonnes  {

    private static ObservableList<Personne> lesPersonnes;

    private static ListChangeListener<Personne> unChangementListener;

    public static void main(String[] args) {

        lesPersonnes = FXCollections.observableArrayList();

        unChangementListener = change -> {
            System.out.println("Dans le Listener :");
            while (change.next()) {
                if (change.wasAdded()) {
                    System.out.println("Ajout : " + change.getAddedSubList());
                }
                if (change.wasRemoved()) {
                    System.out.println("Suppression : " + change.getRemoved());
                }
            }
            System.out.println("Liste actuelle : " + lesPersonnes);
            System.out.println();
        };

        lesPersonnes.addListener(unChangementListener);
        System.out.println("Début du programme");
        System.out.println("Liste actuelle : " + lesPersonnes);
        System.out.println();
        question1();
        System.out.println("Fin de la question 1");
        System.out.println("Liste actuelle : " + lesPersonnes);
        System.out.println();
        question2();
        System.out.println("Fin de la question 2");
        System.out.println("Liste actuelle : " + lesPersonnes);
        System.out.println();
        question3();
        System.out.println("Fin de la question 3");
        System.out.println("Liste actuelle : " + lesPersonnes);
        System.out.println();
        question5();
        System.out.println("Fin de la question 5");
        System.out.println("Liste actuelle : " + lesPersonnes);
        System.out.println();
        System.out.println("Fin du programme");
        System.out.println("Liste actuelle : " + lesPersonnes);
        System.out.println();
        lesPersonnes.removeListener(unChangementListener);
        System.out.println("Fin du programme");
    }

    public static void question1() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
    }

    public static void question2() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        lesPersonnes.remove(paul);
    }

    public static void question3() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        paul.setAge(5);
    }

    public static void question5() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.addAll(pierre, paul, jacques);
        for (Personne p : lesPersonnes)
            p.setAge(p.getAge()+10);
        lesPersonnes.removeAll(paul, pierre);
    }
}

