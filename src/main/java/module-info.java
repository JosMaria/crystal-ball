module org.mariacode.crystalballexercises {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.mariacode.crystalballexercises to javafx.fxml;
    exports org.mariacode.crystalballexercises;
}