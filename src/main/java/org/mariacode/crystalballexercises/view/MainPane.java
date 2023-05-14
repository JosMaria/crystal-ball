package org.mariacode.crystalballexercises.view;

import javafx.scene.layout.VBox;
import org.mariacode.crystalballexercises.inventory.InventorySystemPane;
import org.mariacode.crystalballexercises.shampoo.ShampooSalesPane;

import java.io.IOException;

public class MainPane {

    private static MainPane mainPane;
    private VBox pane;
    private final ShampooSalesPane shampooPane;
    private final InventorySystemPane inventorySystemPane;

    private MainPane() throws IOException {
        shampooPane = new ShampooSalesPane();
        inventorySystemPane = new InventorySystemPane();
        buildPane();

    }

    public synchronized static MainPane getInstance() throws IOException{
        return mainPane != null ? mainPane : new MainPane();
    }

    public VBox getPane() {
        return pane;
    }

    public void buildPane() {
        pane = inventorySystemPane.getPane();
    }
}
