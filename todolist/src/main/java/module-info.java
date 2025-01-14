module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports view;
    opens view to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
}