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
        //file
        Menu File = new Menu("File");
        MenuItem File1 = new MenuItem("File1");
        MenuItem File2 = new MenuItem("File2");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        File.getItems().addAll(File1, separator, File2);
        //Edit
        Menu Edit = new Menu("Edit");
        MenuItem Cut = new MenuItem("Cut");
        MenuItem Paste = new MenuItem("Paste");
        SeparatorMenuItem separator1 = new SeparatorMenuItem();
        Edit.getItems().addAll(Cut, separator1, Paste);
        //Help
        Menu Help = new Menu("Help");
        MenuBar menuBar = new MenuBar(File, Edit, Help); //on met tout nos menue ici pour ensuite definir leur emplacement avec root
        //Boutons a gauche
        Label boutontext = new Label(" Boutons : ");
        Button bouton1 = new Button("Bouton1");
        Button bouton2 = new Button("Bouton2");
        Button bouton3 = new Button("Bouton3");

        VBox boutongauche = new VBox(10, boutontext, bouton1, bouton2, bouton3);
        boutongauche.setAlignment(Pos.CENTER); //placer les bouton au centre
        boutongauche.setPadding(new Insets(5));//espacer les boutons
        HBox bareDeBoutons = new HBox(boutongauche, new Separator(Orientation.VERTICAL)); //separateur vertical

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
        HBox formButtons = new HBox(10, submit, cancel);
        formButtons.setAlignment(Pos.CENTER);
        gridpane.add(formButtons, 0, 3, 2, 1);

        // Footer avec séparateur horizontal
        Separator footerSeparator = new Separator();
        footerSeparator.setOrientation(Orientation.HORIZONTAL);

        Label footer = new Label("Ceci est un label de bas de page");

        VBox footerBox = new VBox(footerSeparator, footer);
        footerBox.setAlignment(Pos.CENTER);
        footerBox.setPadding(new Insets(5));

        root.setBottom(footerBox);








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

