package org.mariacode.crystalballexercises.shampoo;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

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

        this.pane = new VBox(new HBox(20, paneResult(), paneChart()));
    }

    private HBox paneResult() {
        VBox paneLeft = new VBox(
                new Label("= Estadistica = "),
                new Label("# valores"),
                new Label("Minimo"),
                new Label("Media"),
                new Label("Maximo")
        );
        paneLeft.setAlignment(Pos.CENTER);

        VBox paneRight = new VBox(
                new Label("Datos historicos"),
                new Label(String.valueOf(shampooSalesService.getCount())),
                new Label(String.valueOf(shampooSalesService.getValueMin())),
                new Label(String.valueOf(shampooSalesService.getAverage().intValue())),
                new Label(String.valueOf(shampooSalesService.getValueMax()))
        );
        paneRight.setAlignment(Pos.CENTER);
        HBox pane = new HBox(10, paneLeft, paneRight);
        pane.setPadding(new Insets(20));
        return pane;
    }

    private HBox paneChart() {
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

        XYChart.Series<Number, Number> otherSeries = new XYChart.Series<>();
        ObservableList<XYChart.Data<Number, Number>> otherDataPoints = series.getData();

        int otherCounter = 1;
        for (ShampooSalesInformation sales: shampooSalesService.getSales()) {
            XYChart.Data<Number, Number> xyChartOther = new XYChart.Data<>(otherCounter + 1, sales.unitSales() + 100);
            dataPoints.add(xyChartOther);
            otherCounter++;
        }

        chart.getData().addAll(List.of(series, otherSeries));
        chart.setStyle("-fx-border-color: black; -fx-border-width: 4;");

        HBox pane = new HBox(chart);
        pane.setPadding(new Insets(20));
        return pane;
    }
}
