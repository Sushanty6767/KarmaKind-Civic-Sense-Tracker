package com.karmakind.ui;

import com.karmakind.dao.TaskDAO;
import com.karmakind.model.CivicTask;
import com.karmakind.model.Habit;
import com.karmakind.service.KarmaCalculator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainController {
    private final List tasks = new ArrayList<>();
    private final KarmaCalculator calc = new KarmaCalculator();
    private ScheduledExecutorService executor;

    public void startReminder() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(new com.karmakind.service.ReminderService(tasks), 0, 1, TimeUnit.MINUTES);
    }

    public void stopReminder() {
        if (executor != null) executor.shutdownNow();
    }

    public void sampleWorkflow() throws SQLException {
        TaskDAO dao = new TaskDAO();
        Habit h = new Habit(0, "Morning Walk", "Walk 30 minutes", 5, LocalDate.now().plusDays(1), 1);
        CivicTask c = new CivicTask(0, "Community Clean-up", "Beach cleaning", 10, LocalDate.now().plusDays(2), CivicTask.Impact.HIGH, 1);
        try {
            dao.save(h);
            dao.save(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
