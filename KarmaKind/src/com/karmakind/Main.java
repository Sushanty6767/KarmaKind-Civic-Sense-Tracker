package com.karmakind;

import javax.swing.SwingUtilities;
import com.karmakind.core.TaskManager;
import com.karmakind.core.InMemoryTaskManager;
import com.karmakind.ui.MainWindow;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new InMemoryTaskManager();
        SwingUtilities.invokeLater(() -> {
            MainWindow w = new MainWindow(manager);
            w.setVisible(true);
        });
    }
}
