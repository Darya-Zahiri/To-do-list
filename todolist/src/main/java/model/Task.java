package model;

import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeView;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;

public class Task {
    String name;
    String description;
    LocalDate date;
    Subtask child;
    CheckBoxTreeItem<String> checkBox;
    TreeView<String> tree;
    int id;
    static int maxid=-1;

    boolean status;
    //true=to do
    //false=done
    public Task(String name,String description,LocalDate date,int id){
        this.name=name;
        this.description=description;
        this.date=date;
        this.id=id;
        status=true;
        checkBox=new CheckBoxTreeItem<>(this.name);
        tree=new TreeView<>(checkBox);
    }
    public static void addTask(String name,String description,LocalDate date){
        try {
            maxid++;
            Task newtask=new Task(name,description,date,maxid);
            Session.database.executeQueryWithoutResult("insert into task (id,name,description,date) values ("+maxid+",'"+name+"','"+description+"','"+date.toString()+"');");
            Session.getSession().allTasks.add(newtask);
            Session.getSession().allTasks.sort(Comparator.comparing(Task::getDate));
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        for (int i=0;i<Session.getSession().allTasks.size();i++){
            System.out.println(Session.getSession().allTasks.get(i).toString());
        }
    }
    public static void deleteTask(Task task){
        Session.getSession().allTasks.remove(task);
    }
    public String toString(){
        return this.name;
    }
    public Subtask getChild(){
        return child;
    }
    public CheckBoxTreeItem<String> getCheckBox(){
        return checkBox;
    }
    public TreeView<String> getTree(){
        return tree;
    }
    public String getDescription(){
        return description;
    }
    public String getDate(){
        return date.toString();
    }
    public void setName(String name){
        this.name=name;
        checkBox=new CheckBoxTreeItem<>(name);
        tree=new TreeView<>(checkBox);
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setDate(LocalDate date){
        this.date=date;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
}
