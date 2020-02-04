package com.unirail;


public class RouteStation {
    private String startStation = "";
    private String finalStation = "";
    public void setStartStation(String station) {
        startStation = station;
    }
    public void setFinalStation(String station) {
        finalStation = station;
    }
    public String getStartStation(){
        return startStation;
    }
    public String getFinalStation(){
        return finalStation;
    }
}
