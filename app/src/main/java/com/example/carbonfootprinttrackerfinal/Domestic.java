package com.example.carbonfootprinttrackerfinal;


public class Domestic {

    private double totalDomestic;
    private double light;
    private double heat;
    private double waste;
    private double water;

    public Domestic() {

    }
    public Domestic(String id, double totalDomestic, double light, double heat, double waste, double water) {
        super();
        this.totalDomestic = totalDomestic;
        this.light = light;
        this.heat = heat;
        this.waste = waste;
        this.water = water;
    }

    public double getTotalDomestic() {
        return totalDomestic;
    }
    public void setTotalDomestic(double totalDomestic) {
        this.totalDomestic = totalDomestic;
    }
    public double getLight() {
        return light;
    }
    public void setLight(double light) {
        this.light = light;
    }
    public double getHeat() {
        return heat;
    }
    public void setHeat(double heat) {
        this.heat = heat;
    }
    public double getWaste() {
        return waste;
    }
    public void setWaste(double waste) {
        this.waste = waste;
    }
    public double getWater() {
        return water;
    }
    public void setWater(double water) {
        this.water = water;
    }
}


