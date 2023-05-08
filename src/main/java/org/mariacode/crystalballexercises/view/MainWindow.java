package org.mariacode.crystalballexercises.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application {

    public static final String TITLE = "Crystal Ball";

    @Override
    public void start(Stage stage) {
        try {
            VBox pane = MainPane.getInstance().getPane();
            Scene scene = new Scene(pane);
            stage.setScene(scene);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        stage.setTitle(TITLE);
        stage.show();
    }
}
