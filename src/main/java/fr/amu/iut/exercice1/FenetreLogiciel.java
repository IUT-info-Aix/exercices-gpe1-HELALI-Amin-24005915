package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javax.swing.*;


public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");
        BorderPane root = new BorderPane();
        //Menubar
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
        Label boutontext = new Label(" Boutons : ");
        Button bouton1 = new Button("Bouton1");
        Button bouton2 = new Button("Bouton2");
        Button bouton3 = new Button("Bouton3");


        VBox boutongauche = new VBox(boutontext, bouton1, bouton2, bouton3);//on ne peut pas faire comme avec les menu on les met directement dans la VBOX ou Hbox
        boutongauche.setAlignment(Pos.CENTER);
        HBox bareDeBoutons = new HBox(boutongauche, new Separator(Orientation.VERTICAL));



        //TextField aux centre et champ de saisie
        Label Name = new Label("Name :");
        Label Email = new Label("Email :");
        Label Password = new Label("Password :");
        //champs de saisie
        TextField Name1 = new TextField();
        TextField Email1 = new TextField();
        TextField Password1 = new PasswordField();
        //création d'un grid ou il faudra mettre la position des objets
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);//Le placer au centre
        gridpane.setHgap(2);
        gridpane.setVgap(3);
        //Définition des position des Label
        gridpane.add(Name, 0, 0);
        gridpane.add(Email, 0, 1);
        gridpane.add(Password, 0, 2);
        //Définition des position des TextField
        gridpane.add(Name1, 1, 0);
        gridpane.add(Email1, 1, 1);
        gridpane.add(Password1, 1, 2);

        //création et positionnement des boutons
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");

        gridpane.add(submit, 0, 3);
        gridpane.add(cancel, 1, 3);
        submit.setPadding(new Insets(0, 1, 0, 0));
        cancel.setPadding(new Insets(0, 1, 0, 0));





        root.setCenter(gridpane);

        //Texte et boutons a gauche
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

