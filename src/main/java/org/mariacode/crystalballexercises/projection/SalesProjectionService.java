package org.mariacode.crystalballexercises.projection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

import static org.mariacode.crystalballexercises.projection.Growth.*;

public class SalesProjectionService {

    private final List<Growth> inputGrowth = List.of(
            YEAR_1_Q1, YEAR_1_Q2, YEAR_1_Q3, YEAR_1_Q4,
            YEAR_2_Q1, YEAR_2_Q2, YEAR_2_Q3, YEAR_2_Q4,
            YEAR_3_Q1, YEAR_3_Q2, YEAR_3_Q3, YEAR_3_Q4
    );

    public ObservableList<SalesInfo> dataToTable(SalesProjectionInput input) {
        ObservableList<SalesInfo> rows = FXCollections.observableArrayList();

        int startingSales = input.startingSales();
        for (Growth growth: inputGrowth) {
            int endingSales = (int) ((startingSales * growth.getGrowth()) / 100) + startingSales;
            rows.add(new SalesInfo(startingSales, growth.getGrowth(), endingSales));
            startingSales = endingSales;
        }

        return rows;
    }

    public List<Growth> getInputGrowth() {
        return inputGrowth;
    }
}
