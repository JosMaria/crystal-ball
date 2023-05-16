package org.mariacode.crystalballexercises.projection;

public class SalesInfo {

    private final Integer startingSales;
    private final Double growth;
    private final Integer endingSales;

    public SalesInfo(Integer startingSales, Double growth, Integer endingSales) {
        this.startingSales = startingSales;
        this.growth = growth;
        this.endingSales = endingSales;
    }

    public Integer getStartingSales() {
        return startingSales;
    }

    public Double getGrowth() {
        return growth;
    }

    public Integer getEndingSales() {
        return endingSales;
    }
}
