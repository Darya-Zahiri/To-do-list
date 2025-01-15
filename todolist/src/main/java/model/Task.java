package model;

import java.time.LocalDate;

public class Task {
    String name;
    String description;
    LocalDate date;
    Subtask child;
    //deadline
    boolean status;
    //true=to do
    //false=done
    public Task(String name,String description,LocalDate date){
        this.name=name;
        this.description=description;
        this.date=date;
        status=true;
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
}
