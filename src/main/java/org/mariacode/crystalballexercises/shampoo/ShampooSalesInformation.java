package org.mariacode.crystalballexercises.shampoo;

import java.time.LocalDate;

public record ShampooSalesInformation(LocalDate date, int unitSales) {

    @Override
    public String toString() {
        return String.format("{ %s-%s\t%s }", date.getDayOfMonth(), date.getMonth(), unitSales);
    }
}
