package org.mariacode.crystalballexercises.projection;

public enum Growth {

    YEAR_1_Q1(3),
    YEAR_1_Q2(2.5),
    YEAR_1_Q3(3.5),
    YEAR_1_Q4(3.5),
    YEAR_2_Q1(3),
    YEAR_2_Q2(4),
    YEAR_2_Q3(4.5),
    YEAR_2_Q4(5.5),
    YEAR_3_Q1(4.5),
    YEAR_3_Q2(5),
    YEAR_3_Q3(5.5),
    YEAR_3_Q4(5.5);

    private final double growth;

    Growth(double growth) {
        this.growth = growth;
    }

    public double getGrowth() {
        return growth;
    }
}
