package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javax.swing.*;


public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");
        BorderPane root = new BorderPane();
 ///////Menubar
        Menu menu1 = new Menu("File");
        MenuItem File1 = new MenuItem("File1");
        MenuItem File2 = new MenuItem("File2");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        menu1.getItems().addAll(File1, separator, File2);

        Menu menu2 = new Menu("Edit");
        MenuItem Cut = new MenuItem("Cut");
        MenuItem Paste = new MenuItem("Paste");
        SeparatorMenuItem separator1 = new SeparatorMenuItem();
        menu2.getItems().addAll(Cut, separator1, Paste);

        Menu menu3 = new Menu("Help");
        MenuItem share = new MenuItem("share");
        MenuItem talk = new MenuItem("talk");
        SeparatorMenuItem separator2 = new SeparatorMenuItem();
        menu3.getItems().addAll(share, separator2, talk);
        MenuBar menuBar = new MenuBar(menu1, menu2, menu3); //on met tout nos menue ici pour ensuite definir leur emplacement avec root

        //Boutons a gauche
        Label boutontext = new Label(" Boutons :");
        Button bouton1 = new Button("Bouton1");
        Button bouton2 = new Button("Bouton2");
        Button bouton3 = new Button("Bouton3");

        VBox boutongauche = new VBox(boutontext, bouton1, bouton2, bouton3); //on ne peut pas faire comme avec les menu on les met directement dans la VBOX ou Hbox
        
        HBox bareDeBoutons = new HBox(boutongauche, new Separator(Orientation.VERTICAL));

        //Texte a gauche
        root.setLeft(bareDeBoutons);
        root.setTop(menuBar);










        VBox vbox = new VBox();
        Scene scene = new Scene(root );
        primaryStage.setScene( scene );
        primaryStage.setWidth( 800 );
        primaryStage.setHeight( 600 );

        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);

    }
}

