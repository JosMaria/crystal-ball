package org.mariacode.crystalballexercises.view;

import javafx.scene.layout.VBox;
import org.mariacode.crystalballexercises.shampoo.ShampooSalesPane;

import java.io.IOException;

public class MainPane {

    private static MainPane mainPane;
    private VBox pane;
    private final ShampooSalesPane shampooPane;

    private MainPane() throws IOException {
        shampooPane = new ShampooSalesPane();
        buildPane();

    }

    public synchronized static MainPane getInstance() throws IOException{
        return mainPane != null ? mainPane : new MainPane();
    }

    public VBox getPane() {
        return pane;
    }

    public void buildPane() {
        pane = shampooPane.getPane();
    }
}
