package org.mariacode.crystalballexercises.projection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SalesProjectionPane {

    private static SalesProjectionPane salesProjectionPane;

    private final SalesProjectionService service;

    private VBox pane;
    private TableView<SalesInfo> table;
    private TextField txtStartingSales;
    private Button btnStart;
    private Group group;
    private BarChart<String, Number> barChart;

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

        HBox pane = new HBox(20, rightPane, new HBox(group));
        pane.setAlignment(Pos.CENTER);
        this.pane = new VBox(pane);
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
        buildGraphicBar();
    }

    private void click_btn_start() {
        int startingSales = Integer.parseInt(txtStartingSales.getText());
        SalesProjectionInput input = new SalesProjectionInput(startingSales);
        ObservableList<SalesInfo> listInput = service.dataToTable(input);
        table.setItems(listInput);
        barChart.setData(FXCollections.observableList(update(listInput)));
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

    private void buildGraphicBar() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.observableArrayList(List.of("Years")));
        xAxis.setLabel("Years - trimestre");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Cantidad");

        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Sales Growth");
        barChart.setBarGap(30);
        barChart.setAnimated(true);
        barChart.getData().addAll(loadDataGraphic());
        group = new Group(barChart);
    }

    private List<XYChart.Series<String, Number>> loadDataGraphic() {
        List<XYChart.Series<String, Number>> list  = new ArrayList<>();
        for (Growth growth: service.getInputGrowth()) {
            XYChart.Series<String, Number> xyChart = new XYChart.Series<>();
            xyChart.setName(growth.name());
            xyChart.getData().add(new XYChart.Data<>("Years" , 0));
            list.add(xyChart);
        }

        return list;
    }

    private List<XYChart.Series<String, Number>> update(List<SalesInfo> listInput) {
        List<XYChart.Series<String, Number>> list  = new ArrayList<>();
        int index = 0;
        for (Growth growth: service.getInputGrowth()) {
            XYChart.Series<String, Number> xyChart = new XYChart.Series<>();
            xyChart.setName(growth.name());
            SalesInfo salesInput = listInput.get(index);
            xyChart.getData().add(new XYChart.Data<>("Years", salesInput.getEndingSales()));
            list.add(xyChart);
            index++;
        }

        return list;
    }

    private <U, T> TableColumn<U, T> column(String titleColumn, String property, double prefSize) {
        TableColumn<U, T> column = new TableColumn<>(titleColumn);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setPrefWidth(prefSize);
        return column;
    }
}
