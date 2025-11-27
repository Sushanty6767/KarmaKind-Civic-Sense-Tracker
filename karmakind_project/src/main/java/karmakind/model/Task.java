package com.karmakind.model;

import java.time.LocalDate;

public abstract class Task implements Trackable {
    protected int id;
    protected String title;
    protected String description;
    protected int basePoints;
    protected LocalDate dueDate;
    protected boolean completed;
    protected int userId;

    public Task() {}

    public Task(int id, String title, String description, int basePoints, LocalDate dueDate, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.basePoints = basePoints;
        this.dueDate = dueDate;
        this.userId = userId;
    }

    public abstract int calculatePoints();

    public void markComplete() {
        this.completed = true;
        onComplete();
    }

    public boolean isDueSoon() {
        if (dueDate == null) return false;
        return !completed && LocalDate.now().plusDays(1).isAfter(dueDate);
    }

    // getters & setters omitted for brevity (add as needed)
}
