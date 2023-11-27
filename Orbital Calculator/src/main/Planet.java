/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author danie
 */
public class Planet {
    private String planetName;
    private double distanceFromSun;
    private double mass; 
    private final double sunMass = 1.99 * Math.pow(10, 30);
    private final double gravitationalConstant = 6.67 * Math.pow(10, -11);
    private ArrayList<Double> xCords = new ArrayList<>();
    private ArrayList<Double> yCords = new ArrayList<>();
    
    public Planet(double distanceFromSun, double mass,String planetName) {
        this.distanceFromSun = distanceFromSun;
        this.mass = mass;
        this.planetName = planetName;
        plotOrbit();
    }
    
    private double gravitationalForce(double xCord,double yCord,double mass1,double mass2){
        return -(this.gravitationalConstant * mass1 * mass2)/((Math.pow(xCord, 2))+Math.pow(yCord, 2));
    }
    
    private void plotOrbit(){
        double maxTime = 24*60*60*365;
        double deltaTime = 24*60*60;
        double currentTime = 0;
        
        double xInitial = 1.5 * Math.pow(10,11);
        this.xCords.add(xInitial);
        double xFinal = xInitial;
        
        double yInitial = 0;
        this.yCords.add(yInitial);
        double yFinal = xInitial;
        
        double xInitialVelocity = 0;
        double xVelocity = xInitialVelocity;
        
        double yInitialVelocity = 30000;
        double yVelocity = yInitialVelocity;
        
        while (currentTime < maxTime){
            currentTime += deltaTime;
            xVelocity = xVelocity + (deltaTime*gravitationalForce(xFinal,yFinal,this.mass,this.sunMass) *
                    xFinal * Math.sqrt(Math.pow(xFinal, 2) + Math.pow(xFinal, 2)))/this.mass;

            yVelocity = yVelocity + (deltaTime*gravitationalForce(xFinal,yFinal,this.mass,this.sunMass) *
                    yFinal * Math.sqrt(Math.pow(xFinal, 2) + Math.pow(xFinal, 2)))/this.mass;

            xFinal = xFinal + deltaTime * xVelocity;
            yFinal = yFinal + deltaTime * yVelocity;

            this.xCords.add(xFinal);
            this.yCords.add(yFinal);
        }
    }
    
    

    public double getDistanceFromSun() {
        return distanceFromSun;
    }

    public void setDistanceFromSun(double distanceFromSun) {
        this.distanceFromSun = distanceFromSun;
        this.xCords.clear();
        this.yCords.clear();
        plotOrbit();
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
        this.xCords.clear();
        this.yCords.clear();
        plotOrbit();
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public ArrayList<Double> getxCords() {
        return xCords;
    }

    public ArrayList<Double> getyCords() {
        return yCords;
    }

}
