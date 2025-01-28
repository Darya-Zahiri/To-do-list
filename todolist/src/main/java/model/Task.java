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
    public static int maxid;

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
    public static void addTask(String name,String description,LocalDate date,int id){
        Task newtask;
        try {
            if(id==-1) {
                maxid++;
                Session.database.executeQueryWithoutResult("insert into task (id,name,description,date) values (" + maxid + ",'" + name + "','" + description + "','" + date.toString() + "');");
                newtask = new Task(name, description, date, maxid);
                Session.getSession().allTasks.add(newtask);
            }else{
                Session.database.executeQueryWithoutResult("update task set name='"+name+"', description='"+description+"' , date='"+date.toString()+"' where (id="+id+");");
            }
            Session.getSession().allTasks.sort(Comparator.comparing(Task::getDate));
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        for (int i=0;i<Session.getSession().allTasks.size();i++){
            System.out.println(Session.getSession().allTasks.get(i).toString());
        }
    }
    public static void deleteTask(Task task){
        try {
            Session.database.executeQueryWithoutResult("delete from subtask where idtask="+task.id+";");

            Session.database.executeQueryWithoutResult("delete from task where id="+task.id+";");
            Session.getSession().allTasks.remove(task);
        }catch (SQLException e){

        }
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
