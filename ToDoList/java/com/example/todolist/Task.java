package com.example.todolist;

public class Task {
    private String name;
    private int ID;
    private int priority;
    private boolean done;

    public Task(String name){
        this.name = name;
        priority = 1;
        done = false;
    }

    public Task(String name, int ID){
        this.name = name;
//        this.priority = priority;
        this.ID = ID;
        this.done = false;
    }

    public int getID(){
        return ID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String toString(){
        return this.name;
    }
}