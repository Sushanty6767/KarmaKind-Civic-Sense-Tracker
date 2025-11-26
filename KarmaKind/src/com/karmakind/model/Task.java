package com.karmakind.model;

public class Task {
    protected int id;
    protected String name;
    protected String description;
    protected String category;
    protected int karmaValue;
    protected int priority;
    protected boolean completed;
    protected String taskType;

    public Task(String name) {
        this.name = name;
        this.taskType = "BASE";
    }

    public int calculateKarma() {
        // base karma - can be overridden
        return karmaValue;
    }

    // Getters/setters
    public void setId(int id){ this.id = id; }
    public int getId(){ return id; }
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    public boolean isCompleted(){ return completed; }
    public void setCompleted(boolean c){ this.completed = c; }
    public int getKarmaValue(){ return karmaValue; }
    public void setKarmaValue(int v){ this.karmaValue = v; }
}
