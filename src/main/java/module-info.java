module org.example.cs215project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.cs215project to javafx.fxml;
    exports org.example.cs215project;
}