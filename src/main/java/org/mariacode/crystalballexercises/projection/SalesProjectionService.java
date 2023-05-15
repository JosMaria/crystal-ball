package org.mariacode.crystalballexercises.projection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SalesProjectionService {

    public ObservableList<SalesInfo> dataToTable(SalesProjectionInput input) {
        ObservableList<SalesInfo> rows = FXCollections.observableArrayList();
        List<Double> inputGrowth = List.of(3.0, 2.5, 3.5, 3.5, 3.0, 4.0, 4.5, 5.5, 4.5, 5.0, 5.5, 5.5);

        int startingSales = input.startingSales();
        for (double growth: inputGrowth) {
            int endingSales = (int) ((startingSales * growth) / 100) + startingSales;
            rows.add(new SalesInfo(startingSales, growth, endingSales));
            startingSales = endingSales;
        }

        return rows;
    }

}
