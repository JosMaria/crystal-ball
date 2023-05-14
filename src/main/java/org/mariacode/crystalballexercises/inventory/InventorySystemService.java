package org.mariacode.crystalballexercises.inventory;

import java.util.List;

public class InventorySystemService {

    public InventoryInfo calculate(DataInput input) {
        int week = 1; //
        int begInvPos = input.initialInventory();
        int begInv = input.initialInventory();
        boolean orderRec = false;
        int unitsRec = orderRec ? input.orderQuantity() : 0;
        final int dmd = 100;
        int endInv = Math.max(0, begInv - dmd + unitsRec);
        int lostSales = getLostSales(dmd, begInvPos, begInv);
        boolean orderPlaced = endInv <= input.reorderPoint();
        int endingInvPos = getEndingInvPos(begInvPos, dmd, lostSales, orderPlaced, input.orderQuantity());
        int weekDue = orderPlaced ? week + input.leadTime() + 1 : 0;
        double holdCost = Math.max(0, endInv * input.holdingCost());
        int orderCost = orderPlaced ? input.orderCost() : 0;
        int shortCost = lostSales * input.lostSalesCost();
        int totalCost = (int) holdCost + orderCost + shortCost;

        return new InventoryInfo(
                week, begInvPos, begInv, orderRec, unitsRec, dmd,
                endInv, lostSales, orderPlaced, endingInvPos,
                weekDue, holdCost, orderCost, shortCost, totalCost);
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

    public static void main(String[] args) {
        InventorySystemService service = new InventorySystemService();
        DataInput input = new DataInput(300, 250, 300, 2, 50, 0.20, 100);
        InventoryInfo row = service.calculate(input);
        System.out.printf(String.format("%s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s",
                row.week(), row.begInvPos(), row.begInv(), row.orderRec(), row.unitRec(), row.dmd(),
                row.endInv(), row.lostSales(), row.orderPlaced(), row.endingInvPos(), row.weekDue(),
                row.holdCost(), row.orderCost(), row.shortCost(), row.totalCost()));
    }
}
