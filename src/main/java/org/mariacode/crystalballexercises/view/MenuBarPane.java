package org.mariacode.crystalballexercises.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.mariacode.crystalballexercises.inventory.InventorySystemPane;
import org.mariacode.crystalballexercises.projection.SalesProjectionPane;
import org.mariacode.crystalballexercises.shampoo.ShampooSalesPane;

import java.io.IOException;

public class MenuBarPane implements EventHandler<ActionEvent> {

    private final VBox mainPane;

    private static final String SHAMPOO_SALES = "Shampoo sales";
    private static final String SALES_PROJECTION = "Sales Projection";
    private static final String INVENTORY_SYSTEM = "Inventory System";

    public MenuBarPane() {
        Label title = new Label("Crystal Ball");
        title.setFont(new Font("Gargi", 30));

        VBox informationPane = new VBox(10,
                new Label("ESTUDIANTE: Jose Maria Aguilar Chambi"),
                new Label("DOCENTE: Jose Richard Ayoroa Cardozo"));
        informationPane.setAlignment(Pos.CENTER);

        VBox paneDefault = new VBox(30, title, informationPane);
        paneDefault.setAlignment(Pos.CENTER);
        paneDefault.setPadding(new Insets(20));

        mainPane = new VBox(10, getMenuBar(), paneDefault);
        mainPane.setMinWidth(1300);
        mainPane.setMinHeight(700);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        MenuItem source = (MenuItem) actionEvent.getSource();
        VBox pane = switch (source.getText()) {
            case SHAMPOO_SALES -> {
                try {
                    yield ShampooSalesPane.getInstance().getPane();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case SALES_PROJECTION -> SalesProjectionPane.getInstance().getPane();
            case INVENTORY_SYSTEM -> InventorySystemPane.getInstance().getPane();
            default -> new VBox(new Label("Empty"));
        };
        changePane(new VBox(pane));
    }

    public VBox getMainPane() {
        return mainPane;
    }

    private MenuBar getMenuBar() {
        return new MenuBar(
                createMenu(SHAMPOO_SALES),
                createMenu(SALES_PROJECTION),
                createMenu(INVENTORY_SYSTEM)
        );
    }

    private Menu createMenu(String title) {
        Menu menu = new Menu(title);
        MenuItem menuItem = new MenuItem(title);
        menu.getItems().add(menuItem);
        menuItem.setOnAction(this);
        return menu;
    }

    private void changePane(Pane pane) {
        mainPane.getChildren().remove(1);
        mainPane.getChildren().add(pane);
    }
}
