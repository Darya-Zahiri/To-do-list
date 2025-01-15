package model;

import java.time.LocalDate;

public class Subtask extends Task{
    Task parent;
    Subtask sibling;
    public Subtask(String name, String description, LocalDate date, Task parent) {
        super(name, description,date);
        this.parent=parent;

    }
    public static boolean addSubtask(String name,String description,LocalDate date,Task parent){
        if (date.isBefore(parent.date)){
            Subtask newsubtask=new Subtask(name,description,date,parent);
            if (parent.child == null){
                parent.child=newsubtask;
            }else {
                Subtask temp= parent.child;
                while (temp.sibling != null){
                    temp=temp.sibling;
                }
                temp.sibling=newsubtask;
            }
            return true;
        }else {
            return false;
        }
    }
    public void deleteSubtask(){
        boolean delete=true;
        status=false;
        Subtask temp= parent.child;
        while (temp!=null){
            if (temp.status==true){
                delete=false;
                break;
            }
            temp=temp.sibling;
        }
        if (delete){
            deleteTask(parent);
        }
    }
}
