package com.karmakind.service;

import com.karmakind.model.Task;
import java.util.List;

public class ReminderService implements Runnable {
    private final List<Task> tasks;

    public ReminderService(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        synchronized (tasks) {
            for (Task t : tasks) {
                if (t != null && !t.completed && t.isDueSoon()) {
                    System.out.println("Reminder: Task due soon -> " + t.title);
                }
            }
        }
    }
}
