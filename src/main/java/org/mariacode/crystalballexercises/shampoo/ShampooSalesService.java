package org.mariacode.crystalballexercises.shampoo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ShampooSalesService {

    private static final String DATA_PATH = "src/main/resources/shampoo-sales-data.txt";

    public ShampooSalesService() {
        try {
            readData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void readData() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(DATA_PATH));
        lines.stream()
                .skip(4)
                .map(this::createShampooInformation)
                .forEach(System.out::println);

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

    public static void main(String[] args) {
        new ShampooSalesService();
    }
}
