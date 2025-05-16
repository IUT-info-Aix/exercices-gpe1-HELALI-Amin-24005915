package fr.amu.iut.exercice17;

public class Exercice {
    private String enonce;
    private int resultatAttendu;
    private boolean reussi;

    public Exercice(String enonce, int resultatAttendu) {
        this.enonce = enonce;
        this.resultatAttendu = resultatAttendu;
        this.reussi = false; // Par défaut, non réussi
    }

    public String getEnonce() {
        return enonce;
    }

    public int getResultatAttendu() {
        return resultatAttendu;
    }

    public boolean verifierReponse(int reponseUtilisateur) {
        this.reussi = (reponseUtilisateur == resultatAttendu);
        return this.reussi;
    }

    public boolean estReussi() {
        return reussi;
    }
} 