package com.karmakind.model;

import java.time.LocalDate;

public class CivicTask extends Task {
    public enum Impact {LOW, MEDIUM, HIGH}
    private Impact impact;

    public CivicTask() {}

    public CivicTask(int id, String title, String desc, int basePoints, LocalDate dueDate, Impact impact, int userId) {
        super(id, title, desc, basePoints, dueDate, userId);
        this.impact = impact;
    }

    @Override
    public int calculatePoints() {
        int multiplier = switch (impact) {
            case LOW -> 1;
            case MEDIUM -> 2;
            case HIGH -> 3;
        };
        return basePoints * multiplier;
    }

    @Override
    public void onComplete() {
        // Could notify community feed in a full app
    }
}
