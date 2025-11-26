package com.karmakind.core;

import com.karmakind.model.Task;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryTaskManager implements TaskManager {
    private List<Task> tasks = Collections.synchronizedList(new ArrayList<>());
    private AtomicInteger seq = new AtomicInteger(1);

    @Override
    public void addTask(Task t){
        t.setId(seq.getAndIncrement());
        tasks.add(t);
    }
    @Override
    public void updateTask(Task t){
        for (int i=0;i<tasks.size();i++){
            if (tasks.get(i).getId() == t.getId()){
                tasks.set(i, t);
                return;
            }
        }
    }
    @Override
    public void deleteTask(int taskId){
        tasks.removeIf(x -> x.getId() == taskId);
    }
    @Override
    public List<Task> listTasks(){
        return new ArrayList<>(tasks);
    }
    @Override
    public int calculateTotalKarma(){
        return tasks.stream().mapToInt(Task::calculateKarma).sum();
    }
}
