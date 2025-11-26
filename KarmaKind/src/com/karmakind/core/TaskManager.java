package com.karmakind.core;

import com.karmakind.model.Task;
import java.util.List;

public interface TaskManager {
    void addTask(Task t);
    void updateTask(Task t);
    void deleteTask(int taskId);
    List<Task> listTasks();
    int calculateTotalKarma();
}
