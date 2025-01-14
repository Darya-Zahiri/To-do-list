package model;

public class Task {
    String name;
    String description;
    Subtask child;
    //deadline
    boolean status;
    //true=to do
    //false=done
    public Task(String name,String description){
        this.name=name;
        this.description=description;
        status=true;
    }
    public void addTask(String name,String description){
        Task newtask=new Task(name,description);
        Session.getSession().allTasks.add(newtask);
    }
    public static void deleteTask(Task task){
        Session.getSession().allTasks.remove(task);
    }
}
