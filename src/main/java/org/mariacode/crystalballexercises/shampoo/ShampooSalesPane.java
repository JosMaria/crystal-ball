package org.mariacode.crystalballexercises.shampoo;

import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ShampooSalesPane {

    private VBox pane;
    private final ShampooSalesService shampooSalesService;

    public ShampooSalesPane() throws IOException {
        shampooSalesService = ShampooSalesService.getInstance();
        buildPane();
    }
    public VBox getPane() {
        return pane;
    }

    private void buildPane() {
        LineChart<Number, Number> chart = new LineChart<>(new NumberAxis(), new NumberAxis());
        chart.setTitle("Unit Sales");
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        ObservableList<XYChart.Data<Number, Number>> dataPoints = series.getData();

        int counter = 1;
        for (ShampooSalesInformation sales: shampooSalesService.getSales()) {
            XYChart.Data<Number, Number> xyChart = new XYChart.Data<>(counter, sales.unitSales());
            dataPoints.add(xyChart);
            counter++;
        }
        chart.getData().add(series);

        this.pane = new VBox(chart);
    }
}
