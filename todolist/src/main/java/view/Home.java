package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Session;
import model.Subtask;

import java.io.IOException;

public class Home {
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private Button task;
    @FXML
    private Button subtask;
    @FXML
    private Button done;
    @FXML
    private Button view;
    @FXML
    private Button edit;
    @FXML
    private AnchorPane anchor;
    public void initialize(){
        //for (int j=0;j< Session.getSession().allTasks.size();j++){
            if (Session.getSession().allTasks.size()>0){
                for(int i=0;i<Session.getSession().allTasks.size();i++){
                    CheckBoxTreeItem<String> rootItem =  Session.getSession().allTasks.get(i).getCheckBox();
                    rootItem.setExpanded(true);
                    //rootItem.setExpanded(true);
                    TreeView<String> tree = Session.getSession().allTasks.get(i).getTree();
                    tree.setEditable(true);

                    tree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
                    rootItem.getChildren().clear();
                    Subtask child=Session.getSession().allTasks.get(i).getChild();
                    while (child != null) {
                        rootItem.getChildren().add(child.getCheckBox());
                        //final CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<String>(child.toString());
                        //rootItem.getChildren().add(checkBoxTreeItem);

                        child=child.getSibling();
                    }
                    anchor.getChildren().add(tree);
                    //tree.setPrefWidth(800);
                    tree.setLayoutX(20+200*i);
                    //tree.setLayoutY(10+100*i);
                }
            }
        //}

    }

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
    public void setDone(ActionEvent event){
        //first tasks;
        for (int i=0;i<Session.getSession().allTasks.size();i++){
            if (Session.getSession().allTasks.get(i).getCheckBox().isSelected()){
                Session.getSession().allTasks.remove(i);
            }
        }
    }
}
