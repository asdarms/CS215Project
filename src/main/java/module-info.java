module org.example.cs215project {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.cs215project to javafx.fxml;
    exports org.example.cs215project;
}