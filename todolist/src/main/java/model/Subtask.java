package model;

import java.time.LocalDate;

public class Subtask extends Task{
    Task parent;
    Subtask rightSibling;
    Subtask leftSibling;
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
                while (temp.rightSibling != null){
                    temp=temp.rightSibling;
                }
                temp.rightSibling=newsubtask;
                newsubtask.leftSibling=temp;
            }
            return true;
        }else {
            return false;
        }
    }
    public void deleteSubtask(){
        if (this==this.parent.child){
            this.parent.child=this.rightSibling;
        }

        if (this.leftSibling!=null){
            this.leftSibling.rightSibling=this.rightSibling;
        }
        if (this.rightSibling!=null){

            this.rightSibling.leftSibling=this.leftSibling;
        }
    }
    public Subtask getRightSibling(){
        return this.rightSibling;
    }
}
