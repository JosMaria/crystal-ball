package org.mariacode.crystalballexercises.projection;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class SalesProjectionPane {

    private static SalesProjectionPane salesProjectionPane;

    private final SalesProjectionService service;

    private VBox pane;
    private TableView<SalesInfo> table;
    private TextField txtStartingSales;
    private Button btnStart;

    private SalesProjectionPane() {
        service = new SalesProjectionService();
        loadControls();
        buildPane();
    }

    private void buildPane() {
        VBox rightPane = new VBox(10,
                new HBox(10, new Label("Starting Sales"), txtStartingSales),
                btnStart,
                table
        );
        rightPane.setPadding(new Insets(20));
        rightPane.setFillWidth(false);
        pane = new VBox(rightPane);
    }

    public static SalesProjectionPane getInstance() {
        return salesProjectionPane == null ? new SalesProjectionPane() : salesProjectionPane;
    }

    public VBox getPane() {
        return pane;
    }

    private void loadControls() {
        txtStartingSales = new TextField();
        btnStart = new Button("Start");
        btnStart.setOnAction(actionEvent -> click_btn_start());
        buildTable();
    }

    private void click_btn_start() {

    }

    private void buildTable() {
        TableColumn<SalesInfo, Integer> colStatingSales = column("Starting Sales", "startingSales", 150);
        TableColumn<SalesInfo, Integer> colGrowth = column("Growth", "growth", 80);
        TableColumn<SalesInfo, Integer> colEndingSales = column("Ending Sales", "endingSales", 120);

        table = new TableView<>();
        table.getColumns().addAll(
                List.of(
                        colStatingSales, colGrowth, colEndingSales
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
