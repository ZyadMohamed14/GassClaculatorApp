package com.example.gassapp.domain.models.model;

import java.io.Serializable;
import java.util.List;

public class Route implements Serializable {

    private double distance;
    private List<String> cities;
    private Double time;
    private Double fuelCost;
    boolean isBestRoute;

    public boolean isBestRoute() {
        return isBestRoute;
    }

    public void setBestRoute(boolean bestRoute) {
        isBestRoute = bestRoute;
    }

    public Route(double distance, List<String> cities) {
        this.distance = distance;
        this.cities = cities;
    }

    public Route( List<String> cities, double distance, double fuelCost, double time) {
        this.cities = cities;
        this.distance = distance;
        this.fuelCost = fuelCost;
        this.time = time;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public void setFuelCost(Double fuelCost) {
        this.fuelCost = fuelCost;
    }

    public Double getTime() {
        return time;
    }

    public Double getFuelCost() {
        return fuelCost;
    }

    public double getDistance() {
        return distance;
    }

    public List<String> getCities() {
        return cities;
    }
}