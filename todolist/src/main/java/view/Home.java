package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private Button task;
    @FXML
    private Button subtask;

    public void setTask(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("task.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setSubtask(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("subtask.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
