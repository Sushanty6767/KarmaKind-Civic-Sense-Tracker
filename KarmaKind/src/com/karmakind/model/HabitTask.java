package com.karmakind.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class HabitTask extends Task {
    private int streak;
    private LocalDate lastCompleted;

    public HabitTask(String name) {
        super(name);
        this.taskType = "HABIT";
    }

    @Override
    public int calculateKarma() {
        // reward small bonus for streaks
        int bonus = streak / 3; // 1 extra point every 3 days
        return Math.max(karmaValue + bonus, 0);
    }

    public void completeToday() {
        LocalDate today = LocalDate.now();
        if (lastCompleted != null && ChronoUnit.DAYS.between(lastCompleted, today) == 1) {
            streak++;
        } else if (lastCompleted == null || ChronoUnit.DAYS.between(lastCompleted, today) > 1) {
            streak = 1;
        }
        lastCompleted = today;
        setCompleted(true);
    }

    // getters/setters
    public int getStreak(){ return streak; }
    public LocalDate getLastCompleted(){ return lastCompleted; }
}
