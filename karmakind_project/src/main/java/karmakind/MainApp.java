package com.karmakind;

import com.karmakind.dao.DatabaseManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // initialize DB
        DatabaseManager.getInstance().initializeSchema();

        BorderPane root = new BorderPane();
        root.setCenter(new Label("KarmaKind — Civic Sense Tracker\nA minimal demo UI is provided. Expand as needed."));
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("KarmaKind");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
