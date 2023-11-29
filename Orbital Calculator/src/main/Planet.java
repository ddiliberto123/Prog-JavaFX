/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import FXMLDocuments.Preset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author danie
 */
public class Planet {
    
    public static HashMap presets = new HashMap<String,Planet>();
    private String planetName;
    private static LineChart theChart;
    private static double gravitationalConstant = 6.67408e-11;
    private double distanceFromSun;
    private double mass; 
    private double sunMass = 2e30;
    private double xInitialVelocity,yInitialVelocity;
    
    
    public Planet(double distanceFromSun, double mass,double xInitialVel,double yInitialVel,String planetName) {
        this.distanceFromSun = distanceFromSun;
        this.mass = mass;
        this.planetName = planetName;
        this.xInitialVelocity = xInitialVel;
        this.yInitialVelocity = yInitialVel; 

    }
    
    private double gravitationalForce(double x,double y,double mass1,double mass2){
        return -gravitationalConstant * mass1 * mass2/(x * x + y * y);
    }
    
    public void plotOrbit(){
        double maxTime = 24*60*60*365 * 1.5;
        double deltaTime = 24*60*60;
        double currentTime = 0;
        
        double xInitial = this.distanceFromSun;
        ArrayList<Double> xCords = new ArrayList<>();
        xCords.add(xInitial);
        double xFinal = xInitial;
        
        double yInitial = 0;
        ArrayList<Double> yCords = new ArrayList<>();
        yCords.add(yInitial);
        double yFinal = yInitial;
        
        
        double xVelocity = this.xInitialVelocity;
        
        
        double yVelocity = this.yInitialVelocity;
        
        while (currentTime < maxTime){
            currentTime = currentTime + deltaTime;
            xVelocity = xVelocity + (deltaTime*gravitationalForce(xFinal,yFinal,this.mass,this.sunMass)
                    * xFinal * Math.pow(xFinal * xFinal + yFinal * yFinal, -0.5))/6e24;

            yVelocity = yVelocity + deltaTime  * gravitationalForce(xFinal,yFinal,this.mass,this.sunMass)
                    * yFinal * Math.pow(xFinal * xFinal + yFinal * yFinal, -0.5)/this.mass;

            xFinal = xFinal + (deltaTime * xVelocity);
            yFinal = yFinal + (deltaTime * yVelocity);

            xCords.add(xFinal);
            yCords.add(yFinal);
            
        }
        XYChart.Series series = new XYChart.Series<>();
        
        series.setName(this.planetName);
        for (int i = 1; i < xCords.size(); i++) 
            series.getData().add(new XYChart.Data(xCords.get(i),yCords.get(i)));
        theChart.getData().add(series);
    }

    public double getDistanceFromSun() {
        return distanceFromSun;
    }

    public void setDistanceFromSun(double distanceFromSun) {
        this.distanceFromSun = distanceFromSun;
        plotOrbit();
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
        plotOrbit();
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public static void setTheChart(LineChart theChart) {
        Planet.theChart = theChart;
        
    }

    public double getxInitalVelocity() {
        return xInitialVelocity;
    }

    public void setxInitalVelocity(double xInitalVelocity) {
        this.xInitialVelocity = xInitalVelocity;
        plotOrbit();
    }

    public double getyInitalVelocity() {
        return yInitialVelocity;
    }

    public void setyInitalVelocity(double yInitalVelocity) {
        this.yInitialVelocity = yInitalVelocity;
        plotOrbit();
    }

    @Override
    public String toString() {
        return this.planetName;
    }
}
