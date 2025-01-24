package model;

import utility.Database;

import java.sql.SQLException;
import java.util.ArrayList;

public class Session {
    public static Session session;
    public ArrayList<Task> allTasks=new ArrayList<>();
    public Task currentTask;
    public Subtask currentSubtask;
    private static Database database;
    private Session(){

    }
    public static Session getSession(){
        if(session == null) {
            session = new Session();
            try {
                database = new Database("127.0.0.1",3306,"todolist","todolist","Z@hmaz123");
                System.out.println("|||connected to database.|||");
            } catch (ClassNotFoundException e) {
                /*throw new RuntimeException(e);*/
                System.out.println(e.toString());
            } catch (SQLException e) {
                /*throw new RuntimeException(e);*/
                System.out.println(e.toString());
            }
        }
        return session;
    }
    public Task getCurrentTask(){
        return currentTask;
    }
    public Subtask getCurrentSubtask(){
        return currentSubtask;
    }
}
