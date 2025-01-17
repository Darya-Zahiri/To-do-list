package model;

import java.util.ArrayList;

public class Session {
    public static Session session;
    public ArrayList<Task> allTasks=new ArrayList<>();
    public Task currentTask;
    private Session(){

    }
    public static Session getSession(){
        if(session == null) {
            session = new Session();
        }
        return session;
    }
    public Task getCurrentTask(){
        return currentTask;
    }
}
