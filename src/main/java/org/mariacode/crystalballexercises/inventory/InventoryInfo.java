package org.mariacode.crystalballexercises.inventory;

public record InventoryInfo(
        int week,
        int begInvPos,
        int begInv,
        boolean orderRec,
        int unitRec,
        int dmd,
        int endInv,
        int lostSales,
        boolean orderPlaced,
        int endingInvPos,
        int weekDue,
        double holdCost,
        int orderCost,
        int shortCost,
        int totalCost

) {
}
