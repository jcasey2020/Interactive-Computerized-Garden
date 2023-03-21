module com.example.finalproject_jacksversion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.finalproject_jacksversion to javafx.fxml;
    exports com.example.finalproject_jacksversion;
}