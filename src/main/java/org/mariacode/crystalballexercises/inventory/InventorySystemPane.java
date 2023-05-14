package org.mariacode.crystalballexercises.inventory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InventorySystemPane {

    private VBox pane;

    private final InventorySystemService service;

    public InventorySystemPane() {
        this.service = InventorySystemService.getInstance();
        buildPane();
    }

    public VBox getPane() {
        return pane;
    }

    private void buildPane() {
        TextField txtOrderQuantity = new TextField();
        TextField txtReorderPoint = new TextField();
        TextField txtInitialInventory = new TextField();
        TextField txtLeadTime = new TextField();
        TextField txtOrderCost = new TextField();
        TextField txtHoldingCost = new TextField();
        TextField txtLostSalesCost = new TextField();


        VBox inputPaneRight = new VBox(
                inputField("Order Quantity", txtOrderQuantity),
                inputField("Reorder Point", txtReorderPoint),
                inputField("Initial Inventory", txtInitialInventory),
                inputField("Lead Time", txtLeadTime)
        );

        VBox inputPaneLeft = new VBox(
                inputField("Order Cost", txtOrderCost),
                inputField("Holding Cost", txtHoldingCost),
                inputField("Lost Sales Cost", txtLostSalesCost)
        );

        this.pane = new VBox(new HBox(20, inputPaneRight, inputPaneLeft));
    }

    private HBox inputField(String textLabel, TextField textField) {
        Label lblOrderQuantity = new Label(textLabel);
        textField.setPrefColumnCount(7);
        HBox pane = new HBox(10, lblOrderQuantity, textField);
        pane.setMinWidth(200);
        pane.setPadding(new Insets(5));
        pane.setAlignment(Pos.CENTER_RIGHT);
        return pane;
    }
}
