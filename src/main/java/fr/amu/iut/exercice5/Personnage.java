package fr.amu.iut.exercice5;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

abstract class Personnage extends Group {
    protected final static double LARGEUR_MOITIE_PERSONNAGE = 10;
    protected final static double LARGEUR_PERSONNAGE = LARGEUR_MOITIE_PERSONNAGE * 2;
    private final Circle corps;
    private String direction;

    public Personnage(String direction, Color couleurContour, Color couleurRemplissage) {
        this.direction = direction;
        corps = new Circle(10, 10, LARGEUR_MOITIE_PERSONNAGE, couleurContour);
        corps.setFill(couleurRemplissage);
        getChildren().add(corps);
    }

    public void deplacerAGauche() {
        //    ****
        //   *    *
        //  *---   *
        //   *    *
        //    ****

        // Sauvegarder la position actuelle
        double oldX = getLayoutX();
        double oldY = getLayoutY();

        //déplacement <----
        if (getLayoutX() >= LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() - LARGEUR_PERSONNAGE);
        }

        // Vérification de l'absence de collision avec des obstacles
        if (estEnCollisionAvecObstacle()) {
            // Rétablir la position si une collision est détectée
            setLayoutX(oldX);
            setLayoutY(oldY);
        } else {
            // Mise à jour de la direction uniquement si le mouvement a réussi
            if (!direction.equals("gauche")) {
                direction = "gauche";
            }
        }
    }

    public void deplacerADroite(double largeurJeu) {
        //    ****
        //   *    *
        //  *   ---*
        //   *    *
        //    ****

        // Sauvegarder la position actuelle
        double oldX = getLayoutX();
        double oldY = getLayoutY();

        //déplacement ---->
        if (getLayoutX() < largeurJeu - LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() + LARGEUR_PERSONNAGE);
        }

        // Vérification de l'absence de collision avec des obstacles
        if (estEnCollisionAvecObstacle()) {
            // Restore position if collision detected
            setLayoutX(oldX);
            setLayoutY(oldY);
        } else {
            // Mise à jour de la direction uniquement si le mouvement a réussi
            if (!direction.equals("droite")) {
                direction = "droite";
            }
        }
    }

    public void deplacerEnBas(double hauteurJeu) {
        // Sauvegarder la position actuelle
        double oldX = getLayoutX();
        double oldY = getLayoutY();

        if (getLayoutY() < hauteurJeu - LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() + LARGEUR_PERSONNAGE);
        }

        // Vérification de l'absence de collision avec des obstacles
        if (estEnCollisionAvecObstacle()) {
            // Rétablir la position si une collision est détectée
            setLayoutX(oldX);
            setLayoutY(oldY);
        } else {
            // Mise à jour de la direction uniquement si le mouvement a réussi
            if (!direction.equals("bas")) {
                direction = "bas";
            }
        }

        //    *****
        //   *     *
        //  *   |   *
        //   *  |  *
        //    *****

    }

    public void deplacerEnHaut(double hauteurJeu) {
        // Sauvegarder la position actuelle
        double oldX = getLayoutX();
        double oldY = getLayoutY();

        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE);
        }

        // Vérification de l'absence de collision avec des obstacles
        if (estEnCollisionAvecObstacle()) {
            // Rétablir la position si une collision est détectée
            setLayoutX(oldX);
            setLayoutY(oldY);
        } else {
            // Mise à jour de la direction uniquement si le mouvement a réussi
            if (!direction.equals("haut")) {
                direction = "haut";
            }
        }
        //    *****
        //   *  |  *
        //  *   |   *
        //   *     *
        //    *****

    }

    boolean estEnCollision(Personnage autrePersonnage) {
        return getBoundsInParent().contains(autrePersonnage.getBoundsInParent())
                || autrePersonnage.getBoundsInParent().contains(getBoundsInParent());
    }

    /**
     * Checks si ce personnage est en collision avec un obstacle
     * @return vrai en cas de collision avec un obstacle, faux sinon
     */
    boolean estEnCollisionAvecObstacle() {
        for (Obstacle obstacle : JeuMain.obstacles) {
            if (getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }

}
