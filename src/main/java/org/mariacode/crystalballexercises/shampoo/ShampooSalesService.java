package org.mariacode.crystalballexercises.shampoo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ShampooSalesService {

    public ShampooSalesService() {
        try {
            readData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static final String DATA_PATH = "src/main/resources/shampoo-sales-data.txt";

    private void readData() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(DATA_PATH));
        lines.stream()
                .skip(4)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        new ShampooSalesService();
    }
}
