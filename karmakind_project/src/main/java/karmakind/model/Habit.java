package com.karmakind.model;

import java.time.LocalDate;

public class Habit extends Task {
    private int streak;

    public Habit() {}

    public Habit(int id, String title, String description, int basePoints, LocalDate dueDate, int userId) {
        super(id, title, description, basePoints, dueDate, userId);
        this.streak = 0;
    }

    @Override
    public int calculatePoints() {
        // simple habit: streak bonuses
        return basePoints + (streak / 7); // small bonus per week-long streak
    }

    @Override
    public void onComplete() {
        streak++;
    }
}
