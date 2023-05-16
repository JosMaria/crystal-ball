package org.mariacode.crystalballexercises.inventory;

public record DataInput(
        int orderQuantity,
        int reorderPoint,
        int initialInventory,
        int leadTime,
        int orderCost,
        double holdingCost,
        int lostSalesCost
) {
}
