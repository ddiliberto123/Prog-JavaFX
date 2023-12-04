/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orbitalcalculator.javafxtermproject;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

/**
 *
 * @author 2271909
 */
public class Planet {
    private static LineChart theChart;
    private static double years = 6;
    public boolean shown = false;
    private String planetName;
    private static double gravitationalConstant = 6.67408e-11;
    private double distanceFromSun;
    private double mass; 
    private double sunMass = 2e30;
    private double xInitialVelocity,yInitialVelocity;
    private Series series = new XYChart.Series<>();
    
    
    public Planet(double distanceFromSun, double mass,double xInitialVel,double yInitialVel,String planetName) {
        this.distanceFromSun = distanceFromSun;
        this.mass = mass;
        this.planetName = planetName;
        this.xInitialVelocity = xInitialVel;
        this.yInitialVelocity = yInitialVel; 
        plotOrbit();
    }
    
    private double gravitationalForce(double x,double y,double mass1,double mass2){
        return -gravitationalConstant * mass1 * mass2/(x * x + y * y);
    }
    
    public void plotOrbit(){
        double maxTime = 24*60*60*365 * years;
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
                    * xFinal * Math.pow(xFinal * xFinal + yFinal * yFinal, -0.5))/(this.mass);

            yVelocity = yVelocity + deltaTime  * gravitationalForce(xFinal,yFinal,this.mass,this.sunMass)
                    * yFinal * Math.pow(xFinal * xFinal + yFinal * yFinal, -0.5)/(this.mass);

            xFinal = xFinal + (deltaTime * xVelocity);
            yFinal = yFinal + (deltaTime * yVelocity);
            
            xCords.add(xFinal);
            yCords.add(yFinal);
            
        }
        
        this.series.setName(this.planetName);
        for (int i = 1; i < xCords.size(); i++) 
            series.getData().add(new XYChart.Data(xCords.get(i)/1.496e11,yCords.get(i)/1.496e11));
    }
    
    public void show(){
        if(!this.shown){
            theChart.getData().add(this.series);
            this.shown = true;
        }
        
    }
    
    public void hide(){
        theChart.getData().remove(this.series);
        this.shown = false;
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
