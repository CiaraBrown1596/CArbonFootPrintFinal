package com.example.carbonfootprinttrackerfinal;


public class Domestic {

    private double totalDomestic;
    private double light;
    private double heat;


    public Domestic() {

    }
    public Domestic(String id, double totalDomestic, double light, double heat) {
        super();
        this.totalDomestic = totalDomestic;
        this.light = light;
        this.heat = heat;
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

}


