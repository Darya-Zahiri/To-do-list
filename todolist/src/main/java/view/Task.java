package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Session;

import java.io.IOException;

public class Task {
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
    private Label result;

    public void initialize(){
        result.setOpacity(0);
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
        if (Session.getSession().currentTask==null){
            model.Task.addTask(name.getText(),description.getText(),deadline.getValue(),-1);
        }else {
            model.Task.addTask(name.getText(),description.getText(),deadline.getValue(),Session.getSession().currentTask.getId());
            Session.getSession().currentTask.setName(name.getText());
            Session.getSession().currentTask.setDescription(description.getText());
            Session.getSession().currentTask.setDate(deadline.getValue());
            Session.getSession().currentTask=null;
        }
        result.setText("successful");
        result.setDisable(false);
        result.setOpacity(0.45);
    }
}
