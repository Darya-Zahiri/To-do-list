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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Session;
import model.Task;

import java.io.IOException;
import java.util.Objects;

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
    private ComboBox<String> task;
    @FXML
    private Label result;
    @FXML
    private Label error;

    public void initialize(){
        result.setOpacity(0);
        error.setOpacity(0);
        for (int i=0;i<Session.getSession().allTasks.size();i++){
            task.getItems().add(Session.getSession().allTasks.get(i).toString());
        }
    }

    public void setBack(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setAdd(ActionEvent event){
        Task parent = null;
        result.setDisable(true);
        result.setOpacity(0);
        String pname=task.getValue();
        for (int i=0;i<Session.getSession().allTasks.size();i++){
            if (Objects.equals(pname, Session.getSession().allTasks.get(i).toString())){
                parent=Session.getSession().allTasks.get(i);
                break;
            }
        }
        if (Session.getSession().currentSubtask==null){
            if (model.Subtask.addSubtask(name.getText(),description.getText(),deadline.getValue(),parent)){
                error.setOpacity(0);
                result.setText("succesful");
                result.setOpacity(0.45);
            }else {
                result.setOpacity(0);
                error.setText("error! dates didnt match!");
                error.setOpacity(0.45);
            }
        }else {

            if (Session.getSession().currentSubtask.setParent(parent)){
                Session.getSession().currentSubtask.setName(name.getText());
                Session.getSession().currentSubtask.setDescription(description.getText());
                Session.getSession().currentSubtask.setDate(deadline.getValue());
                error.setOpacity(0);
                result.setText("succesful");
                result.setOpacity(0.45);
                Session.getSession().currentSubtask=null;
            }else {
                Session.getSession().currentSubtask=null;
                result.setOpacity(0);
                error.setText("error! dates didnt match!");
                error.setOpacity(0.45);
            }
        }


    }
}
