package org.mariacode.crystalballexercises.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventorySystemService {

    private static InventorySystemService instance;

    private double totalHoldCost;
    private int totalOrderCost;

    private InventorySystemService() {
        setValueInitial();
    }

    public static InventorySystemService getInstance() {
        return instance == null ? new InventorySystemService() : instance;
    }

    private void setValueInitial() {
        totalHoldCost = 0;
        totalOrderCost = 0;
    }

    public double getTotalHoldCost() {
        return totalHoldCost;
    }

    public int getTotalOrderCost() {
        return totalOrderCost;
    }

    public ObservableList<InventoryInfo> dataToTable(DataInput input) {
        setValueInitial();

        final int dmd = 100;
        ObservableList<InventoryInfo> rows = FXCollections.observableArrayList();

        boolean isFirstIteration = true;
        int begInvPos, begInv, unitsRec, endInv = 0, lostSales,
            endingInvPos = 0, weekDue, orderCost, shortCost, totalCost;
        double holdCost;
        boolean orderRec = false, orderPlaced;

        int week = 1;
        for (; week <= 52; week++) {
            if (isFirstIteration) {
                begInvPos = input.initialInventory();
                begInv = input.initialInventory();
                orderPlaced = endInv <= input.reorderPoint();
                isFirstIteration = false;

            } else {
                begInvPos = endingInvPos;
                begInv = endInv;
                orderPlaced = (begInvPos - dmd) <= input.reorderPoint();
                orderRec = (week - 1) % 3 == 0;
            }
            unitsRec = orderRec ? input.orderQuantity() : 0;
            endInv = Math.max(0, begInv - dmd + unitsRec);
            lostSales = getLostSales(dmd, begInvPos, begInv);
            endingInvPos = getEndingInvPos(begInvPos, dmd, lostSales, orderPlaced, input.orderQuantity());
            weekDue = orderPlaced ? week + input.leadTime() + 1 : 0;
            holdCost = Math.max(0, endInv * input.holdingCost());
            orderCost = orderPlaced ? input.orderCost() : 0;
            shortCost = lostSales * input.lostSalesCost();
            totalCost = (int) holdCost + orderCost + shortCost;

            totalHoldCost += holdCost;
            totalOrderCost += orderCost;

            rows.add(new InventoryInfo(
                    week, begInvPos, begInv, orderRec, unitsRec, dmd,
                    endInv, lostSales, orderPlaced, endingInvPos,
                    weekDue, holdCost, orderCost, shortCost, totalCost));
        }

        return rows;
    }

    private int getLostSales(int dmd, int begInv, int unitRec) {
        if (dmd > begInv + unitRec) {
            return (begInv + unitRec >= 0) ? dmd - begInv - unitRec : dmd;
        }

        return 0;
    }

    private int getEndingInvPos(int begInvPos, int dmd, int lostSales, boolean orderPlaced, int orderQuantity) {
        int temp = begInvPos - dmd + lostSales;
        int conditional = orderPlaced ? orderQuantity : 0;
        return temp + conditional;
    }
}
