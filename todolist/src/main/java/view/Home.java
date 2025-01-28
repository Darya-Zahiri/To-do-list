package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Session;
import model.Subtask;
import model.Task;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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
    @FXML
    private TextArea show;
    public void initialize(){
        //for (int j=0;j< Session.getSession().allTasks.size();j++){
            if (Session.getSession().allTasks.size()==0){
                ResultSet taskResultset;
                ResultSet subtaskResultset;
                try {
                    taskResultset=Session.database.executeQueryWithResult("select max(id) from task;");
                    if(taskResultset.next())
                        Task.maxid=taskResultset.getInt("max(id)");
                    else
                        Task.maxid=-1;
                    System.out.println("maxid="+Task.maxid);
                    taskResultset=Session.database.executeQueryWithResult("select max(id) from subtask;");
                    if(taskResultset.next())
                        Subtask.maxidsub=taskResultset.getInt("max(id)");
                    else
                        Subtask.maxidsub=-1;
                    System.out.println("maxsubid="+Subtask.maxidsub);
                    taskResultset=Session.database.executeQueryWithResult("select * from task;");
                    if(taskResultset!=null){
                        //taskResultset.beforeFirst();
                        while (taskResultset.next()){
                            String taskname,description,taskdate;
                            LocalDate date;
                            int taskid;
                            taskid=taskResultset.getInt("id");
                            description=taskResultset.getString("description");
                            taskname=taskResultset.getString("name");
                            taskdate=taskResultset.getString("date");
                            date=LocalDate.parse(taskdate);
                            Task task=new Task(taskname,description,date,taskid);

                            int subtaskid;
                            subtaskResultset=Session.database.executeQueryWithResult("select * from subtask where idtask="+taskid+";");
                            while (subtaskResultset.next()){
                                subtaskid=subtaskResultset.getInt("id");
                                description=subtaskResultset.getString("description");
                                taskname=subtaskResultset.getString("name");
                                taskdate=subtaskResultset.getString("date");
                                date=LocalDate.parse(taskdate);
                                Subtask.addSubtask(taskname,description,date,task,subtaskid,1);
                            }
                            Session.getSession().allTasks.add(task);
                        }
                    }else {
                        System.out.println("error");
                    }
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
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

                child=child.getRightSibling();
            }
            anchor.getChildren().add(tree);
            //tree.setPrefWidth(800);
            tree.setLayoutX(20+300*i);
            //tree.setLayoutY(10+100*i);
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
        if (Session.getSession().allTasks.size()!=0){
            int i=0;
            while (i<Session.getSession().allTasks.size()){
                Subtask child=Session.getSession().allTasks.get(i).getChild();
                while (child!=null){
                    if(child.getCheckBox().isSelected()){
                        Session.getSession().allTasks.get(i).getTree().getChildrenUnmodifiable().remove(child.getCheckBox());
                        child.deleteSubtask();
                    }
                    child=child.getRightSibling();
                }
                if (Session.getSession().allTasks.get(i).getCheckBox().isSelected()){
                    Session.getSession().allTasks.get(i).getTree().setOpacity(0);
                    Task.deleteTask(Session.getSession().allTasks.get(i));
                }

                i++;
            }
        }

    }

    public void setView(ActionEvent event) {
        String output = "";
        if (Session.getSession().allTasks.size() != 0) {
            for (int i = 0; i < Session.getSession().allTasks.size(); i++) {

                if (Session.getSession().allTasks.get(i).getCheckBox().isSelected()) {
                    Task temp = Session.getSession().allTasks.get(i);
                    output += "kind : Task " +"\n"+ "name : " + temp.toString() +"\n"+ "description : " + temp.getDescription() +
                            "\n"+ "deadline : " + temp.getDate()+"\n";
                    break;
                }

                Subtask child = Session.getSession().allTasks.get(i).getChild();
                while (child != null) {
                    if (child.getCheckBox().isSelected()) {
                        output += "kind : SubTask " +"\n"+ "name : " + child.toString() +"\n"+ "description : " + child.getDescription() +
                                "\n"+"deadline : " + child.getDate() +"\n"+ "Task : " + child.getParent().toString()+"\n";
                    }
                    child = child.getRightSibling();
                }
            }
        }
        show.setText(output);
    }

    public void setEdit(ActionEvent event) throws IOException {
        if (Session.getSession().allTasks.size() != 0) {
            for (int i = 0; i < Session.getSession().allTasks.size(); i++) {

                if (Session.getSession().allTasks.get(i).getCheckBox().isSelected()) {
                    Session.getSession().currentTask=Session.getSession().allTasks.get(i);
                    Session.getSession().currentSubtask=null;
                    root = FXMLLoader.load(getClass().getResource("task.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    break;
                }

                Subtask child = Session.getSession().allTasks.get(i).getChild();
                while (child != null) {
                    if (child.getCheckBox().isSelected()) {
                        Session.getSession().currentSubtask=child;
                        Session.getSession().currentTask=null;
                        root = FXMLLoader.load(getClass().getResource("subtask.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        break;
                    }
                    child = child.getRightSibling();
                }
            }
        }
    }
}
