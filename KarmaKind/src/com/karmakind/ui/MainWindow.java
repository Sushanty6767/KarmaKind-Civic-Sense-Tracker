package com.karmakind.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.karmakind.core.TaskManager;
import com.karmakind.core.InMemoryTaskManager;
import com.karmakind.model.Task;
import com.karmakind.model.HabitTask;
import com.karmakind.model.BehaviourTask;
import java.util.List;

public class MainWindow extends JFrame {
    private TaskManager manager;
    private DefaultListModel<Task> listModel;
    private JList<Task> taskList;
    private JLabel karmaLabel;

    public MainWindow(TaskManager manager) {
        super("KarmaKind — Civic Sense Tracker");
        this.manager = manager;
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel main = new JPanel(new BorderLayout(10,10));
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setCellRenderer(new DefaultListCellRenderer(){
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                Task t = (Task)value;
                String text = String.format("%d - %s [%s] - karma:%d", t.getId(), t.getName(), t.getClass().getSimpleName(), t.getKarmaValue());
                return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
            }
        });

        JButton addBtn = new JButton("Add Task");
        addBtn.addActionListener(e -> addTaskDialog());

        JButton completeBtn = new JButton("Complete");
        completeBtn.addActionListener(e -> {
            Task sel = taskList.getSelectedValue();
            if (sel != null) {
                sel.setCompleted(true);
                manager.updateTask(sel);
                refreshList();
            }
        });

        karmaLabel = new JLabel("Total Karma: 0");

        JPanel top = new JPanel();
        top.add(addBtn);
        top.add(completeBtn);
        top.add(karmaLabel);

        main.add(new JScrollPane(taskList), BorderLayout.CENTER);
        main.add(top, BorderLayout.NORTH);
        add(main);
        refreshList();
    }

    private void addTaskDialog(){
        JTextField nameField = new JTextField(20);
        String[] types = {"Task", "Habit", "Behaviour"};
        JComboBox<String> typeBox = new JComboBox<>(types);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Name:")); panel.add(nameField);
        panel.add(new JLabel("Type:")); panel.add(typeBox);
        int res = JOptionPane.showConfirmDialog(this, panel, "Create Task", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION){
            String name = nameField.getText().trim();
            String type = (String)typeBox.getSelectedItem();
            Task t;
            if ("Habit".equals(type)) t = new HabitTask(name);
            else if ("Behaviour".equals(type)) t = new BehaviourTask(name, "community");
            else t = new Task(name);
            t.setKarmaValue(5);
            manager.addTask(t);
            refreshList();
        }
    }

    private void refreshList(){
        listModel.clear();
        List<Task> tasks = manager.listTasks();
        for (Task t: tasks) listModel.addElement(t);
        karmaLabel.setText("Total Karma: " + manager.calculateTotalKarma());
    }
}
