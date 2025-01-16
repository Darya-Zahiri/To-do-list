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
    private TreeView<String> tree;
    @FXML
    private AnchorPane anchor;
    public void initialize(){
        for (int j=0;j< Session.getSession().allTasks.size();j++){
            CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<String>(Session.getSession().allTasks.get(j).toString());
            rootItem.setExpanded(true);
            tree = new TreeView(rootItem);
            tree.setEditable(true);

            tree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
            Subtask child=Session.getSession().allTasks.get(j).getChild();
            while (child != null) {
                final CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<String>(child.toString());
                rootItem.getChildren().add(checkBoxTreeItem);
                child=child.getSibling();
            }
        }
        anchor.getChildren().add(tree);
        tree.setPrefWidth(800);
        tree.setLayoutX(10);
        tree.setLayoutY(10);
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
}
