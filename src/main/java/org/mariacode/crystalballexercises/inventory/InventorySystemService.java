package org.mariacode.crystalballexercises.inventory;

import java.util.ArrayList;
import java.util.List;

public class InventorySystemService {

    public List<InventoryInfo> dataToTable(DataInput input) {
        final int dmd = 100;

        List<InventoryInfo> rows = new ArrayList<>();
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

    public static void main(String[] args) {
        InventorySystemService service = new InventorySystemService();
        DataInput input = new DataInput(300, 250, 300, 2, 50, 0.20, 100);
        service.dataToTable(input)
                        .forEach(InventorySystemService::print);
    }

    static void print(InventoryInfo row) {
        System.out.printf(String.format("%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s    %s\t\t%s\t\t%s\n",
                row.week(), row.begInvPos(), row.begInv(), row.orderRec(), row.unitRec(), row.dmd(),
                row.endInv(), row.lostSales(), row.orderPlaced(), row.endingInvPos(), row.weekDue(),
                row.holdCost(), row.orderCost(), row.shortCost(), row.totalCost()));
    }
}
