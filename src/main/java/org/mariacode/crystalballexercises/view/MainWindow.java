package org.mariacode.crystalballexercises.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {

    public static final String TITLE = "Crystal Ball";

    @Override
    public void start(Stage stage) {
        MenuBarPane menuBarPane = new MenuBarPane();
        Scene scene = new Scene(menuBarPane.getMainPane());
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
    }
}
