package org.mariacode.crystalballexercises.inventory;

public class InventoryInfo {

        private final int week;
        private final int begInvPos;
        private final int begInv;
        private final boolean orderRec;
        private final int unitRec;
        private final int dmd;
        private final int endInv;
        private final int lostSales;
        private final boolean orderPlaced;
        private final int endingInvPos;
        private final int weekDue;
        private final double holdCost;
        private final int orderCost;
        private final int shortCost;
        private final int totalCost;

    public InventoryInfo(int week, int begInvPos, int begInv, boolean orderRec, int unitRec,
                         int dmd, int endInv, int lostSales, boolean orderPlaced, int endingInvPos,
                         int weekDue, double holdCost, int orderCost, int shortCost, int totalCost) {
        this.week = week;
        this.begInvPos = begInvPos;
        this.begInv = begInv;
        this.orderRec = orderRec;
        this.unitRec = unitRec;
        this.dmd = dmd;
        this.endInv = endInv;
        this.lostSales = lostSales;
        this.orderPlaced = orderPlaced;
        this.endingInvPos = endingInvPos;
        this.weekDue = weekDue;
        this.holdCost = holdCost;
        this.orderCost = orderCost;
        this.shortCost = shortCost;
        this.totalCost = totalCost;
    }

    public int getWeek() {
        return week;
    }

    public int getBegInvPos() {
        return begInvPos;
    }

    public int getBegInv() {
        return begInv;
    }

    public boolean isOrderRec() {
        return orderRec;
    }

    public int getUnitRec() {
        return unitRec;
    }

    public int getDmd() {
        return dmd;
    }

    public int getEndInv() {
        return endInv;
    }

    public int getLostSales() {
        return lostSales;
    }

    public boolean isOrderPlaced() {
        return orderPlaced;
    }

    public int getEndingInvPos() {
        return endingInvPos;
    }

    public int getWeekDue() {
        return weekDue;
    }

    public double getHoldCost() {
        return holdCost;
    }

    public int getOrderCost() {
        return orderCost;
    }

    public int getShortCost() {
        return shortCost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "InventoryInfo{" +
                "week=" + week +
                ", begInvPos=" + begInvPos +
                ", begInv=" + begInv +
                ", orderRec=" + orderRec +
                ", unitRec=" + unitRec +
                ", dmd=" + dmd +
                ", endInv=" + endInv +
                ", lostSales=" + lostSales +
                ", orderPlaced=" + orderPlaced +
                ", endingInvPos=" + endingInvPos +
                ", weekDue=" + weekDue +
                ", holdCost=" + holdCost +
                ", orderCost=" + orderCost +
                ", shortCost=" + shortCost +
                ", totalCost=" + totalCost +
                '}';
    }
}
