package org.mariacode.crystalballexercises.inventory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class InventorySystemPane {

    private VBox pane;

    private final InventorySystemService service;

    private TextField txtOrderQuantity, txtReorderPoint, txtInitialInventory, txtLeadTime, txtOrderCost, txtHoldingCost, txtLostSalesCost;
    private TableView<InventoryInfo> table;
    private Button btnStart;
    private Label lblResultFinal;

    public InventorySystemPane() {
        this.service = InventorySystemService.getInstance();
        loadControls();
        buildPane();
    }

    public VBox getPane() {
        return pane;
    }

    private void loadControls() {
        txtOrderQuantity = new TextField();
        txtReorderPoint = new TextField();
        txtInitialInventory = new TextField();
        txtLeadTime = new TextField();
        txtOrderCost = new TextField();
        txtHoldingCost = new TextField();
        txtLostSalesCost = new TextField();

        btnStart = new Button("Empezar");
        btnStart.setOnAction(eventHandler -> click_btn_start());

        buildTable();

        lblResultFinal = new Label();
    }

    private void click_btn_start() {
        readData();
    }

    private void readData() {
        int orderQuantity = Integer.parseInt(txtOrderQuantity.getText());
        int reorderPoint = Integer.parseInt(txtReorderPoint.getText());
        int initialInventory = Integer.parseInt(txtInitialInventory.getText());
        int leadTime = Integer.parseInt(txtLeadTime.getText());
        int orderCost = Integer.parseInt(txtOrderCost.getText());
        double holdingCost = Double.parseDouble(txtHoldingCost.getText());
        int lostSalesCost = Integer.parseInt(txtLostSalesCost.getText());

        DataInput input = new DataInput(orderQuantity, reorderPoint, initialInventory, leadTime, orderCost, holdingCost, lostSalesCost);
        table.setItems(service.dataToTable(input));

        String textFinalResult = String.format("""
                        Total Annual Costs
                        -----------------------------
                        $ %s  |  $ %s  |  $ %s""",
                service.getTotalHoldCost(),
                service.getTotalOrderCost(),
                service.getTotalHoldCost() + service.getTotalOrderCost());
        lblResultFinal.setText(textFinalResult);
    }

    private void buildPane() {
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

        HBox inputPane = new HBox(20, inputPaneRight, inputPaneLeft);

        VBox rightPane = new VBox(10, inputPane, btnStart);
        rightPane.setAlignment(Pos.CENTER);

        VBox leftPane = new VBox(10, lblResultFinal);

        HBox topPane = new HBox(20, rightPane, leftPane);
        topPane.setAlignment(Pos.CENTER);
        VBox pane = new VBox(20, topPane, table);
        pane.setPadding(new Insets(20));
        this.pane = pane;
    }

    private HBox inputField(String textLabel, TextField textField) {
        Label lblOrderQuantity = new Label(textLabel);
        textField.setPrefColumnCount(5);
        HBox pane = new HBox(10, lblOrderQuantity, textField);
        pane.setMinWidth(200);
        pane.setPadding(new Insets(5));
        pane.setAlignment(Pos.CENTER_RIGHT);
        return pane;
    }

    private void buildTable() {
        TableColumn<InventoryInfo, Integer> colWeek = column("week", "week", 80);
        TableColumn<InventoryInfo, Integer> colBegInvPos = column("beg\nInv\nPos", "begInvPos", 80);
        TableColumn<InventoryInfo, Double> colBegInv = column("beg\nInv", "begInv", 90);
        TableColumn<InventoryInfo, Boolean> colOrderRec = column("order\nRec", "orderRec", 80);
        TableColumn<InventoryInfo, Integer> colUnitRec = column("unit\nRec", "unitRec", 80);
        TableColumn<InventoryInfo, Integer> colDmd = column("Dmd", "dmd", 80);
        colDmd.setStyle("-fx-background-color: lightgreen");
        TableColumn<InventoryInfo, Integer> colEndInv = column("end\nInv", "endInv", 90);
        TableColumn<InventoryInfo, Integer> colLostSales = column("lost\nSales", "lostSales", 90);
        TableColumn<InventoryInfo, Boolean> colOrderPlaced = column("order\nPlaced", "orderPlaced", 90);
        TableColumn<InventoryInfo, Integer> colEndingInvPos = column("ending\nInv\nPos", "endingInvPos", 90);
        TableColumn<InventoryInfo, Integer> colWeekDue = column("week\nDue", "weekDue", 80);
        TableColumn<InventoryInfo, Double> colHoldCost = column("hold\nCost", "holdCost", 80);
        TableColumn<InventoryInfo, Integer> colOrderCost = column("order\nCost", "orderCost", 80);
        TableColumn<InventoryInfo, Integer> colShortCost = column("short\nCost", "shortCost", 80);
        TableColumn<InventoryInfo, Integer> colTotalCost = column("total\nCost", "totalCost", 80);

        table = new TableView<>();
        table.getColumns().addAll(
                List.of(
                        colWeek, colBegInvPos, colBegInv, colOrderRec, colUnitRec, colDmd, colEndInv, colLostSales,
                        colOrderPlaced, colEndingInvPos, colWeekDue, colHoldCost, colOrderCost, colShortCost, colTotalCost
                )
        );
    }

    private <U, T> TableColumn<U, T> column(String titleColumn, String property, double prefSize) {
        TableColumn<U, T> column = new TableColumn<>(titleColumn);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setPrefWidth(prefSize);
        return column;
    }
}
