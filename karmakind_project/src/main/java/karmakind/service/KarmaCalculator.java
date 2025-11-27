package com.karmakind.service;

import com.karmakind.model.CivicTask;
import com.karmakind.model.Habit;
import com.karmakind.model.Task;

public class KarmaCalculator {
    public int calculate(Task t) {
        if (t instanceof CivicTask) {
            return t.calculatePoints();
        } else if (t instanceof Habit) {
            return t.calculatePoints();
        } else {
            return t.calculatePoints();
        }
    }
}
