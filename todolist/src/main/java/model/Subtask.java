package model;

import java.sql.SQLException;
import java.time.LocalDate;

public class Subtask extends Task{
    Task parent;
    Subtask rightSibling;
    Subtask leftSibling;
    public static int maxidsub;
    public Subtask(String name, String description, LocalDate date,int id, Task parent) {
        super(name, description,date,id);
        this.parent=parent;

    }
    public static boolean addSubtask(String name,String description,LocalDate date,Task parent,int mode){

        if (date.isBefore(parent.date)){
            try {
                maxidsub++;
                if(mode==0){
                    Session.database.executeQueryWithoutResult("insert into subtask (id,name,description,date,idtask) values ("+maxidsub+",'"+name+"','"+description+"','"+date.toString()+"',"+parent.id+");");
                }
                Subtask newsubtask=new Subtask(name,description,date,maxidsub,parent);
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
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            }
        }else {
            return false;
        }
    }
    public void deleteSubtask(){
        try {
            Session.database.executeQueryWithoutResult("delete from subtask where id="+this.id+";");
            if (this == this.parent.child) {
                this.parent.child = this.rightSibling;
            }

            if (this.leftSibling != null) {
                this.leftSibling.rightSibling = this.rightSibling;
            }
            if (this.rightSibling != null) {

                this.rightSibling.leftSibling = this.leftSibling;
            }
        }catch (SQLException e){

        }
    }
    public Subtask getRightSibling(){
        return this.rightSibling;
    }
    public Task getParent(){
        return parent;
    }
    public boolean setParent(Task parent){
        if (date.isBefore(parent.date)){
            this.deleteSubtask();
            this.parent=parent;
            if (parent.child!=null){
                Subtask temp= parent.child;
                while (temp.rightSibling != null){
                    temp=temp.rightSibling;
                }
                temp.rightSibling=this;
                this.leftSibling=temp;
                this.rightSibling=null;
            } else {
                parent.child=this;
                this.rightSibling=null;
                this.leftSibling=null;
            }
            return true;
        }else {
            return false;
        }
    }
}
