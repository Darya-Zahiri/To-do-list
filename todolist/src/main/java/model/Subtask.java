package model;

public class Subtask extends Task{
    Task parent;
    Subtask sibling;
    public Subtask(String name, String description,Task parent) {
        super(name, description);
        this.parent=parent;

    }
    public boolean addSubtask(String name,String description,Task parent){
        Subtask newsubtask=new Subtask(name,description,parent);
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
