package model;

import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeView;

import java.time.LocalDate;

public class Task {
    String name;
    String description;
    LocalDate date;
    Subtask child;
    CheckBoxTreeItem<String> checkBox;
    TreeView<String> tree;

    boolean status;
    //true=to do
    //false=done
    public Task(String name,String description,LocalDate date){
        this.name=name;
        this.description=description;
        this.date=date;
        status=true;
        checkBox=new CheckBoxTreeItem<>(this.name);
        tree=new TreeView<>(checkBox);
    }
    public static void addTask(String name,String description,LocalDate date){
        Task newtask=new Task(name,description,date);
        Session.getSession().allTasks.add(newtask);
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
}
