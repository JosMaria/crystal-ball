module org.mariacode.crystalballexercises {
    requires javafx.controls;
    requires org.jfree.jfreechart;
    requires javafx.graphics;
    requires javafx.graphicsEmpty;

    opens org.mariacode.crystalballexercises;
    opens org.mariacode.crystalballexercises.view;
    opens org.mariacode.crystalballexercises.inventory;
    opens org.mariacode.crystalballexercises.projection;
}