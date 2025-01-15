package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Session;
import model.Task;

import java.io.IOException;

public class Subtask {
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private Button back;
    @FXML
    private Button add;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private DatePicker deadline;
    @FXML
    private ComboBox task;
    @FXML
    private Label result;

    public void initialize(){
        result.setOpacity(0);
        task = new ComboBox<Task>(FXCollections.observableArrayList(Session.getSession().allTasks));
        System.out.println(task.getItems().toString());
    }

    public void setBack(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setAdd(ActionEvent event){
        result.setDisable(true);
        result.setOpacity(0);
        //if (model.Subtask.addSubtask(name.getText(),description.getText(),deadline.getValue(),task.getValue()))
    }
}
