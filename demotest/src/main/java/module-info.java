module com.example.demotest {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.demotest to javafx.fxml;
    exports com.example.demotest;
    exports view;
    opens view to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
}