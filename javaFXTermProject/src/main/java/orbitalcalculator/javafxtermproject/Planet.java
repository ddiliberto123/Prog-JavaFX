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
 * Creates a planet
 * @author 2271909
 */
public class Planet {
    private static LineChart theChart;
    private static boolean AU = true;
    private static double years = 1;
    /**
     * If the planet is being shown inside the program
     */
    public boolean shown = false;
    private String planetName;
    private static double gravitationalConstant = 6.67408e-11;
    private double distanceFromSun;
    private double mass; 
    private double sunMass = 2e30;
    private double xInitialVelocity,yInitialVelocity;
    private Series series;
    
    /**
     * Constructs a planet class
     * @param distanceFromSun The furthest distance/starting distance from the sun
     * @param mass The mass of the planet
     * @param xInitialVel The initial x velocity
     * @param yInitialVel The initial y velocity
     * @param planetName The planet name
     */
    public Planet(double distanceFromSun, double mass,double xInitialVel,double yInitialVel,String planetName) {
        this.distanceFromSun = distanceFromSun;
        this.mass = mass;
        this.planetName = planetName;
        this.xInitialVelocity = xInitialVel;
        this.yInitialVelocity = yInitialVel;
        //Plots the orbit
        plotOrbit();
    }
    
    private double gravitationalForce(double x,double y,double mass1,double mass2){
        //Calculates the gravitational force at each point - its negative because its a pulling force.
        return -gravitationalConstant * mass1 * mass2/(x * x + y * y);
    }
    /**
     * Plots the orbit of the planet and stores the x,y values inside a series
     */
    public void plotOrbit(){
        //Converts years into seconds
        double maxTime = 24*60*60*365 * years;
        //Converts days into seconds
        double deltaTime = 24*60*60;
        double currentTime = 0;
        
        //Initializes all the arrays... these will eventually become the planet's series.
        //By series I mean its x,y positions on the chart
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
        
        //Loops until the desired trajectory is plotted
        while (currentTime < maxTime){
            //Adds the difference in time to the initial
            currentTime = currentTime + deltaTime;
            /*
            Calculates the new xVelocity at this new time
            Uses vf = vi + 1/2at^2
            Where
                F = ma -> a = F/m
                vi + 1/2*F/m
            The pow -.5 comes from the angle since the velocity is only wanted in one direction (x).
            The forumla without that, would give the x+y directional velocity
            */ 
            xVelocity = xVelocity + (deltaTime*gravitationalForce(xFinal,yFinal,this.mass,this.sunMass)
                    * xFinal * Math.pow(xFinal * xFinal + yFinal * yFinal, -0.5))/(this.mass);

            yVelocity = yVelocity + deltaTime  * gravitationalForce(xFinal,yFinal,this.mass,this.sunMass)
                    * yFinal * Math.pow(xFinal * xFinal + yFinal * yFinal, -0.5)/(this.mass);

            /*
            Takes the new velocities times it by the delta time and adds the initial x/y
            This it the new x
            x2 = x1 + vt
            */
            xFinal = xFinal + (deltaTime * xVelocity);
            yFinal = yFinal + (deltaTime * yVelocity);
            //Adds the cords to the cordinate arrays
            xCords.add(xFinal);
            yCords.add(yFinal);
            
        }
        //Creates the series for the planet
        this.series = new XYChart.Series<>();
        this.series.setName(this.planetName);
        //Adds the arrays into the series, to be plotted
        for (int i = 1; i < xCords.size(); i++) {
            //Didn't have enough time to do convert between m & AU units. :(
            //Simply divides the cords by the m to AU conversion
            if (AU){
                series.getData().add(new XYChart.Data(xCords.get(i)/1.496e11,yCords.get(i)/1.496e11));
            }
            else
                series.getData().add(new XYChart.Data(xCords.get(i),yCords.get(i)));
        }
    }
    
    /**
     * Shows the orbit on the chart
     */
    public void show(){
        theChart.getData().add(this.series);
        this.shown = true;
        
    }
    
    /**
     * Hides the orbit on the chart
     */
    public void hide(){
        theChart.getData().remove(this.series);
        this.shown = false;
    }

    /**
     * Gets the distance from the sun from the planet
     * @return The {@code double} distanceFromSun
     */
    public double getDistanceFromSun() {
        return distanceFromSun;
    }

    /**
     * Sets the distance from the sun for the planet
     * @param distanceFromSun  {@code double} value
     */
    public void setDistanceFromSun(double distanceFromSun) {
        this.distanceFromSun = distanceFromSun;
        plotOrbit();
    }

    /**
     * Gets the mass of the planet
     * @return The {@code double} mass
     */
    public double getMass() {
        return mass;
    }

    /**
     * Sets the mass of the planet
     * @param mass {@code double} value
     */
    public void setMass(double mass) {
        this.mass = mass;
        plotOrbit();
    }

    /**
     * Gets the planet name
     * @return The {@code String} planet name
     */
    public String getPlanetName() {
        return planetName;
    }

    /**
     * Sets the planet name
     * @param planetName {@code String} value
     */
    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    /**
     * Sets the {@code LineChart} for the planet to be plotted upon
     * @param theChart The {@code LineChart} to plot upon
     */
    public static void setTheChart(LineChart theChart) {
        Planet.theChart = theChart;
        
    }

    /**
     * Gets the initial x velocity
     * @return The {@code Double} initial x velocity
     */
    public double getxInitialVelocity() {
        return xInitialVelocity;
    }

    /**
     * Sets the initial x velocity
     * @param xInitalVelocity {@code double} value
     */
    public void setxInitialVelocity(double xInitalVelocity) {
        this.xInitialVelocity = xInitalVelocity;
        plotOrbit();
    }
    /**
     * Gets the initial y velocity
     * @return The {@code Double} initial y velocity
     */
    public double getyInitialVelocity() {
        return yInitialVelocity;
    }
    
    /**
     * Sets the initial y velocity
     * @param yInitalVelocity {@code double} value
     */
    public void setyInitialVelocity(double yInitalVelocity) {
        this.yInitialVelocity = yInitalVelocity;
        plotOrbit();
    }

    /**
     * Converts the planet into a string for the user
     * @return {@link #getPlanetName()}
     */
    @Override
    public String toString() {
        return this.planetName;
    }
    
    /**
     * Gets the years the trajectory will be plotted for
     * @return The {@code double} years
     */
    public static double getYears() {
        return years;
    }

    /**
     * Sets the amount of years the trajectory will be plotted for
     * @param years {@code double} value
     */
    public static void setYears(double years) {
        Planet.years = years;
    }

    /**
     * Gets the units of the current chart
     * @return {@code boolean} value
     */
    public static boolean isAU() {
        return AU;
    }

    /**
     * Sets the units of the current chart
     * @param AU {@code boolean} value
     */
    public static void setAU(boolean AU) {
        Planet.AU = AU;
    }
    
    
}
