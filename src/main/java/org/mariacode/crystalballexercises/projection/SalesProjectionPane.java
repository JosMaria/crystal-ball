package org.mariacode.crystalballexercises.projection;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SalesProjectionPane {

    private static SalesProjectionPane salesProjectionPane;

    private VBox pane;

    private SalesProjectionPane() {
        buildPane();
    }

    private void buildPane() {
        pane = new VBox(new Label("Hola mundo"));
    }

    public static SalesProjectionPane getInstance() {
        return salesProjectionPane == null ? new SalesProjectionPane() : salesProjectionPane;
    }

    public VBox getPane() {
        return pane;
    }
}
