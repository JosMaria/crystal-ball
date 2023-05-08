package org.mariacode.crystalballexercises.shampoo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class ShampooSalesService {

    private static final String DATA_PATH = "src/main/resources/shampoo-sales-data.txt";

    private static ShampooSalesService shampooSalesService;
    private final List<ShampooSalesInformation> sales;

    private ShampooSalesService() throws IOException {
        sales = readData();
        sales.forEach(System.out::println);
    }

    public static ShampooSalesService getInstance() throws IOException {
        return shampooSalesService == null ? new ShampooSalesService() : shampooSalesService;
    }

    public List<ShampooSalesInformation> getSales() {
        return sales;
    }

    public long getCount() {
        return sales.size();
    }

    private int getValueMax() {
        return getValueGivenOperation((unitSale, valueMin) -> unitSale > valueMin);
    }

    private int getValueMin() {
        return getValueGivenOperation((unitSale, valueMin) -> unitSale < valueMin);
    }

    private int getValueGivenOperation(BiPredicate<Integer, Integer> operation) {
        boolean isFirstTime = true;
        int value = 0;
        for (ShampooSalesInformation shampoo: sales) {
            if (isFirstTime) {
                value = shampoo.unitSales();
                isFirstTime = false;

            } else {
                if (operation.test(shampoo.unitSales(), value)) {
                    value = shampoo.unitSales();
                }
            }
        }
        return value;
    }

    private double getAverage() {
        return sales.stream()
                .mapToInt(ShampooSalesInformation::unitSales)
                .average()
                .orElse(0.0);
    }

    private List<ShampooSalesInformation> readData() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(DATA_PATH));
        return lines.stream()
                .skip(4)
                .map(this::createShampooInformation)
                .collect(Collectors.toCollection(ArrayList::new));

    }

    private ShampooSalesInformation createShampooInformation(String line) {
        String[] stringSplit = line.split(",");
        LocalDate date = convertToLocalDate(stringSplit[0]);
        int unitSales = convertToUnitSales(stringSplit[1]);

        return new ShampooSalesInformation(date, unitSales);
    }

    private LocalDate convertToLocalDate(String date) {
        String[] dateSplit = date.split("-");
        int dayOfMonth = Integer.parseInt(dateSplit[0]);
        int numberOfMonth = convertToMonth(dateSplit[1]);

        return LocalDate.of(2023, numberOfMonth, dayOfMonth);
    }

    private Integer convertToUnitSales(String unitSales) {
        return Integer.parseInt(unitSales.trim().replace(".", ""));
    }

    private int convertToMonth(String monthAbbreviated) {
        return switch (monthAbbreviated) {
            case "ene" -> 1;
            case "feb" -> 2;
            case "mar" -> 3;
            case "abr" -> 4;
            case "may" -> 5;
            case "jun" -> 6;
            case "jul" -> 7;
            case "ago" -> 8;
            case "sept" -> 9;
            case "oct" -> 10;
            case "nov" -> 11;
            case "dic" -> 12;
            default -> 0;
        };
    }
}
