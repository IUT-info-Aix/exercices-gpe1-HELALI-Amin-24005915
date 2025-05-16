module tp.intro.javafx {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.desktop;
    exports fr.amu.iut.exercice1;
    exports fr.amu.iut.exercice2;
    opens com.example.partie3 to javafx.fxml;
    opens fr.amu.iut.exercice1 to javafx.fxml;
    opens fr.amu.iut.exercice2 to javafx.fxml;
    opens fr.amu.iut.exercice3 to javafx.fxml;
    opens fr.amu.iut.exercice7 to javafx.fxml;
    opens fr.amu.iut.exercice8 to javafx.fxml;
    exports fr.amu.iut.exercice3;
    exports fr.amu.iut.exercice4;
    exports fr.amu.iut.exercice5;
    exports fr.amu.iut.exercice6;
    exports fr.amu.iut.exercice7;
    exports fr.amu.iut.exercice8;
    exports fr.amu.iut.exercice9;
    exports fr.amu.iut.exercice10;
    exports fr.amu.iut.exercice11;
    opens fr.amu.iut.exercice10 to javafx.fxml;
    opens fr.amu.iut.exercice11 to javafx.fxml;
    exports com.example.partie3;
    exports fr.amu.iut.Exemple1;
    opens fr.amu.iut.Exemple1 to javafx.fxml;
    
    exports fr.amu.iut.exercice12;
    exports fr.amu.iut.exercice13;
    exports fr.amu.iut.exercice14;
    exports fr.amu.iut.exercice15;
    exports fr.amu.iut.exercice16;
    
    opens fr.amu.iut.exercice12 to javafx.fxml;
    opens fr.amu.iut.exercice13 to javafx.fxml;
    opens fr.amu.iut.exercice14 to javafx.fxml;
    opens fr.amu.iut.exercice15 to javafx.fxml;
    opens fr.amu.iut.exercice16 to javafx.fxml;
    
    exports fr.amu.iut.exercice17;
    exports fr.amu.iut.exercice18;
    
    opens fr.amu.iut.exercice17 to javafx.fxml;
    opens fr.amu.iut.exercice18 to javafx.fxml;
}
